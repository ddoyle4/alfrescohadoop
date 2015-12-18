#!/bin/bash
#place this in the hadoop/share/hadoop dir and change destination path

for file in `find ./ -name '*.jar'`
do
xpath=${file%/*}
xbase=${file##*/}
eval cp $file "/home/hadoop/Desktop/test/$xbase"
done
