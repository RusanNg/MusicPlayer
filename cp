#!/bin/bash
git add --all
read -p "Commite Message: " str
git commit -m "$str"
git push
exit()
