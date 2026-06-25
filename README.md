# 📊 GitHub User Activity
CLI application to explore user's activity on Github. 

Inspired by the [GitHub User Acitvity](https://roadmap.sh/projects/github-user-activity) project from [roadmap.sh](https://roadmap.sh/dashboard).


## 🛠️ Tech Stack
* **Language:** Java 17 or higher
* **Build Tool:** Maven 3.8.7
* **API**: GitHub REST API (public events endpoint)

## ⬇️ Installation
Clone the repository:
```bash
git clone https://github.com/etssu/github_user_activity.git
cd github_user_activity
```
Build the project:
```
mvn clean package
```

### 🚀 Run with CLI wrapper (recommended)
For easier usage, a wrapper script is provided.

### Make it executable (Linux / Git Bash / WSL)
```bash
chmod +x github-activity
```

### Run the application:
```
./github-activity <username>
```

### Example:
```
./github-activity kamranahmedse
```

## Output examples
```
Output:
- Pushed 3 commits to kamranahmedse/developer-roadmap
- Opened a new issue in kamranahmedse/developer-roadmap
- Starred kamranahmedse/developer-roadmap
```

