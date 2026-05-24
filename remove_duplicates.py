import xml.etree.ElementTree as ET
import os

strings_path = r"D:\downloads\Metrolist-main\Metrolist-main\app\src\main\res\values\strings.xml"
metrolist_strings_path = r"D:\downloads\Metrolist-main\Metrolist-main\app\src\main\res\values\metrolist_strings.xml"

# Parse strings.xml
tree1 = ET.parse(strings_path)
root1 = tree1.getroot()

# Collect all names in strings.xml
strings_names = set()
for child in root1:
    if 'name' in child.attrib:
        strings_names.add(child.attrib['name'])

print(f"Found {len(strings_names)} resources in strings.xml")

# Parse metrolist_strings.xml using a regex or simple text processing to preserve comments and formatting,
# or just use ElementTree but that ruins formatting.
# Actually, since it's an XML file, removing lines based on names might be tricky if they span multiple lines (like plurals).
# Let's do it with ElementTree and rewrite it.

tree2 = ET.parse(metrolist_strings_path)
root2 = tree2.getroot()

to_remove = []
for child in root2:
    if 'name' in child.attrib and child.attrib['name'] in strings_names:
        to_remove.append(child)

print(f"Found {len(to_remove)} duplicate resources in metrolist_strings.xml")

for child in to_remove:
    root2.remove(child)

# Write back to metrolist_strings.xml
tree2.write(metrolist_strings_path, encoding='utf-8', xml_declaration=True)
print("Finished writing metrolist_strings.xml")
