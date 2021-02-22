#!/bin/bash -e

function lookup_jackson_versions0() {
  curl -v -s 'http://search.maven.org/solrsearch/select?q=g:%22com.fasterxml.jackson%22+AND+a:%22jackson-bom%22&core=gav&rows=1000&wt=json' |
  jq -r '[.response.docs | .[].v |
         {p: split("."), v:.} |
         {major:.p[0] | tonumber, minor: .p[1] | tonumber, v:.v} |
         select(.major >= 2 and .minor >= 4)] |
         sort_by([.major, .minor, .v]) |
         .[].v' |
  grep -E -v '(rc|pr)' |
  grep -E -v '\.\d{8}'
}

function lookup_jackson_versions() {
  logfile=$(mktemp)
  for _ in {1..30}; do
    if lookup_jackson_versions0 2>"$logfile"; then
      return 0
    fi
    sleep 1
  done
  >&2 echo "Failed to get jackson versions: "
  >&2 cat "$logfile"
  exit 1
}

jackson_versions=$(lookup_jackson_versions)

echo "Found Jackson versions: "
echo "${jackson_versions}"

for v in ${jackson_versions}; do
  echo
  echo "Testing Jackson $v"
  echo "========================================================================"
  set -x
  mvn -nsu -B -Djackson.bom.version="$v" -pl jackson surefire:test
  set +x
done

