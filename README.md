[![Build Status](https://travis-ci.org/peel/jrv.svg?branch=master)](https://travis-ci.org/peel/jrv)[![Stories in Ready](https://badge.waffle.io/peel/jrv.png?label=ready&title=Ready)](https://waffle.io/peel/jrv)

jrv
===

JRV is a simple command-line java regex validator.

#Usage

```bash
Usage: jrv [options]

  -r <pattern> | --regex <pattern>
        regex is a required pattern property
  -d <directory> | --dir <directory>
        directory that shall be scanned for filenames
  -b <basename> | --base-name <basename>
        use a defined file basename instead of generated one
  --help
        prints this usage text
```

#Sample

## real directory selection
```bash
$ jrv -r ".*" -d ~/Desktop
matches in /home/peel/Desktop:
- export.txt
- settings.xml
- jrv.md
- arch
- get_transfer_configs.sql
```

## filename generation
Using `-b` switch allows generation of a pseudo-random manipulation of the filename basing upon a predefined base name.
```bash
$ jrv -r ".*.txt" -b "text.txt"
-- matches for .*.txt in . --------------------------
- tdsT4txt
- test.txt
- test.txt
-- dropped -------------------------------------------------------
- TeS7FTxm
- tEStYN7O
```
