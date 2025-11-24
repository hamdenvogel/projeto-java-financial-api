import json
import urllib.request
import base64

# SonarQube credentials
token = "sqp_7cadbda59b1b6f7173e45d22f84747a1956080b1"
# Fetch duplication metrics for files
url = "http://localhost:9000/api/measures/component_tree?component=projeto-financial-api&metricKeys=duplicated_lines,duplicated_blocks,duplicated_lines_density&qualifiers=FIL&ps=100"

# Create request with authentication
auth_str = f"{token}:"
b64_auth = base64.b64encode(auth_str.encode()).decode()
headers = {"Authorization": f"Basic {b64_auth}"}

try:
    request = urllib.request.Request(url, headers=headers)
    response = urllib.request.urlopen(request, timeout=10)
    data = json.loads(response.read().decode())

    print(f"{'File':<80} | {'Lines':<10} | {'Blocks':<10} | {'Density':<10}")
    print("-" * 120)

    for component in data['components']:
        measures = {m['metric']: m['value'] for m in component['measures']}
        if 'duplicated_lines' in measures and float(measures['duplicated_lines']) > 0:
            print(f"{component['key']:<80} | {measures.get('duplicated_lines', '0'):<10} | {measures.get('duplicated_blocks', '0'):<10} | {measures.get('duplicated_lines_density', '0')}%")

except Exception as e:
    print(f"Error fetching metrics: {e}")
