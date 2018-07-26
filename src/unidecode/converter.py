from pathlib import Path
import re

data = re.compile(r"^data = \(")
indentation = re.compile(r"(?m)^'(.*?)',\s+#")
closing = re.compile(r"(?m)^\)")
comments = re.compile(r"(?m)^[ \t\f\v]*?#([^'])")
triple = re.compile(r'"""')
empty = re.compile(r"'',")
single = re.compile(r"\\'")

for file in Path("unidecode/").iterdir():
    name = file.stem
    namespace = f"(ns clj-unidecode.maps.{name})"
    filename = f"maps/{name}.clj"
    with open(file) as f:
        lines = f.read()

    lines = data.sub(f"{namespace}\n(def data\n  [", lines)
    lines = indentation.sub(r'   "\1"  ;;', lines)
    lines = closing.sub(r"])", lines)
    lines = comments.sub(r"   ;;\1", lines)
    lines = triple.sub(r'"\""', lines)
    lines = empty.sub(r'""', lines)
    lines = single.sub(r"'", lines)

    with open(filename, "w") as f:
        f.write(lines)
