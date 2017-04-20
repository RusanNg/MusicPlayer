#!/bin/bash
# this is a test program
git add --all
read -p "Commite Message: " str
git commit -m str
git push
exit(0)
