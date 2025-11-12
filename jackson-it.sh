#!/bin/bash -e

function lookup_jackson_versions0() {
  # TODO: paginate
  curl -v -L -s "https://search.maven.org/solrsearch/select?q=g:%22com.fasterxml.jackson%22+AND+a:%22jackson-bom%22&core=gav&start=0&rows=100&wt=json" |
  jq -r '[.response.docs | .[].v |
         {p: split("."), v:.} |
         {major:.p[0] | tonumber, minor: .p[1] | tonumber, v:.v} |
         select(.major >= 2 and .minor >= 12)] |
         sort_by([.major, .minor, .v]) |
         .[].v' |
  grep -v rc |       # omit rc versions
  grep -v pr |       # omit pr versions
  grep -v -E '[[:digit:]]{8}' # omit "snapshot" (?) versions
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

if [ "$CI" == "" ]; then
  SPLIT="cat"
else
  SPLIT="circleci tests split"
fi
export SPLIT

jackson_versions=$(lookup_jackson_versions)
split_versions=$(echo "$jackson_versions" | $SPLIT)

echo "Found Jackson versions: "
echo "${jackson_versions}"
echo
echo "Testing Jackson versions in this split: "
echo "${split_versions}"

for v in ${split_versions}; do
  echo
  echo "Testing Jackson $v"
  echo "========================================================================"
  set -x
  ./mvnw -nsu -B -V -Dautomatter.jackson.bom.version="$v" -pl jackson surefire:test
  set +x
done

