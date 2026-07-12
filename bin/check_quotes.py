import re
import sys
import glob

fix = "--fix" in sys.argv
if fix:
    sys.argv.remove("--fix")

RE_VAL = re.compile(r"(=\s*)'([^']*)'")

errors = []
for path in glob.glob("**/*.pw.toml", recursive=True):
    with open(path) as f:
        content = f.read()

    new_content = content
    for m in reversed(list(RE_VAL.finditer(content))):
        val = m.group(2)
        if not val or "\\" in val or '"' in val:
            continue
        start, end = m.start(), m.end()
        new_content = new_content[:start] + f'{m.group(1)}"{val}"' + new_content[end:]

    changed = new_content != content
    if fix:
        if changed:
            with open(path, "w") as f:
                f.write(new_content)
            print(f"Fixed: {path}")
    else:
        if changed:
            errors.append(f"{path}: single-quoted values found (run with --fix to convert)")

if errors:
    for e in errors:
        print(e)
    sys.exit(1)
