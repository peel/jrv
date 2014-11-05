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
  --help
        prints this usage text
```

#Sample

```bash
$ jrv -r ".*" --dir ~/Desktop

matches in /home/peel/Desktop:
- export.txt
- settings.xml
- jrv.md
- arch
- get_transfer_configs.sql
```
