#!/bin/bash -e
mvn install -DskipTests=true
for v in $(curl -s 'http://search.maven.org/solrsearch/select?q=g:%22com.fasterxml.jackson.core%22+AND+a:%22jackson-databind%22&core=gav&rows=100&wt=json' |
           jq -r '[.response.docs | .[].v |
                  {p: split("."), v:.} |
                  {major:.p[0] | tonumber, minor: .p[1] | tonumber, v:.v} |
                  select(.major >= 2 and .minor >= 4)] |
                  sort_by([.major, .minor, .v]) |
                  .[].v' |
           grep -v '2.4.0-rc')
do
  echo
  echo "Testing Jackson $v"
  echo "========================================================================"
  set -x
  mvn -Djackson.version=$v -pl jackson test
  set +x
done

