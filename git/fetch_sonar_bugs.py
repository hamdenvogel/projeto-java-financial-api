import json
import urllib.request
import base64

# SonarQube credentials
token = "sqp_7cadbda59b1b6f7173e45d22f84747a1956080b1"
url = "http://localhost:9000/api/issues/search?componentKeys=projeto-financial-api&resolved=false&types=BUG"

# Create request with authentication
auth_str = f"{token}:"
b64_auth = base64.b64encode(auth_str.encode()).decode()
headers = {"Authorization": f"Basic {b64_auth}"}

try:
    request = urllib.request.Request(url, headers=headers)
    response = urllib.request.urlopen(request, timeout=10)
    data = json.loads(response.read().decode())

    print(f"Total bugs: {data['total']}\n")
    print("=" * 100)

    for issue in data['issues']:
        print(f"Component: {issue['component']}")
        print(f"Line: {issue.get('line', 'N/A')}")
        print(f"Message: {issue['message']}")
        print(f"Rule: {issue['rule']}")
        print("-" * 100)

except Exception as e:
    print(f"Error fetching issues: {e}")
