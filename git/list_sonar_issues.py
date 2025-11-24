import json
import urllib.request
import base64

# SonarQube credentials
token = "sqp_7cadbda59b1b6f7173e45d22f84747a1956080b1"
url = "http://localhost:9000/api/issues/search?componentKeys=projeto-financial-api&resolved=false&ps=100"

# Create request with authentication
auth_str = f"{token}:"
b64_auth = base64.b64encode(auth_str.encode()).decode()
headers = {"Authorization": f"Basic {b64_auth}"}

request = urllib.request.Request(url, headers=headers)
response = urllib.request.urlopen(request)
data = json.loads(response.read().decode())

# Group issues by file
issues_by_file = {}
for issue in data['issues']:
    file_path = issue['component'].split(':')[-1]
    if file_path not in issues_by_file:
        issues_by_file[file_path] = []
    issues_by_file[file_path].append({
        'line': issue.get('line', 'N/A'),
        'message': issue['message'],
        'rule': issue['rule'],
        'severity': issue['severity']
    })

# Print organized output
print(f"Total issues: {data['total']}\n")
print("=" * 100)

for file_path in sorted(issues_by_file.keys()):
    print(f"\n📁 {file_path}")
    print("-" * 100)
    for issue in issues_by_file[file_path]:
        print(f"  Line {issue['line']:>4}: [{issue['severity']}] {issue['message']}")
        print(f"           Rule: {issue['rule']}")
    print()
