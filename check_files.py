import tomllib
import os
import sys
import glob

base_dir = sys.argv[1] if len(sys.argv) > 1 else "."

for toml_path in glob.glob(f"{base_dir}/**/*.toml", recursive=True):
    toml_dir = os.path.dirname(toml_path)

    try:
        with open(toml_path, "rb") as f:
            data = tomllib.load(f)
    except Exception as e:
        print(f"Warning: could not parse {toml_path}: {e}")
        continue

    filename = data.get("filename")
    if not filename:
        continue

    target_path = os.path.join(toml_dir, filename)

    if os.path.exists(target_path):
        new_path = target_path + ".disabled"
        os.rename(target_path, new_path)
        print(f"Renamed: {target_path} -> {new_path}")
