# Correct Jenkins Commands for Selenium Testing

## Current Issue
Your Jenkins command has an error:
```bash
# WRONG - has invalid -Dsurefire parameter
mvn clean test -Dsurefire -Durl=http://54.167.3.87:8080/login -Dusr=admin_vp -Dpass=admin_vp -DsShotPath=C:\jenkins\screnshots
```

## Correct Commands

### Option 1: Basic Test Execution
```bash
mvn clean test -Durl=http://54.167.3.87:8080/login -Dusr=admin_vp -Dpass=admin_vp -DsShotPath=C:\jenkins\screenshots
```

### Option 2: With Browser Selection
```bash
mvn clean test -Durl=http://54.167.3.87:8080/login -Dusr=admin_vp -Dpass=admin_vp -DsShotPath=C:\jenkins\screenshots -Dbrowser=chrome
```

### Option 3: Force TestNG Execution
```bash
mvn clean test -Dtest=VProfile_TestSuite -Durl=http://54.167.3.87:8080/login -Dusr=admin_vp -Dpass=admin_vp -DsShotPath=C:\jenkins\screenshots
```

## Jenkins Job Configuration

### Build Step (Windows Batch Command):
```batch
REM Create screenshots directory if it doesn't exist
if not exist "C:\jenkins\screenshots" mkdir "C:\jenkins\screenshots"

REM Run the tests
mvn clean test -Durl=%url% -Dusr=%usr% -Dpass=%pass% -DsShotPath=C:\jenkins\screenshots
```

### Parameters to Add in Jenkins Job:
- **url**: String parameter, default: `http://54.167.3.87:8080/login`
- **usr**: String parameter, default: `admin_vp`
- **pass**: String parameter, default: `admin_vp`
- **browser**: String parameter, default: `chrome`

### Post-build Actions:
1. **Archive artifacts**: `target/surefire-reports/**, C:\jenkins\screenshots/**`
2. **Publish TestNG results**: `target/surefire-reports/testng-results.xml`

## Troubleshooting

### If tests still don't run:
```bash
# Debug command to see what tests are found
mvn test -Dtest=DevOPS.devOPS.VProfile_TestSuite

# Verbose TestNG execution
mvn test -Dtestng.verbose=10
```

### Check test compilation:
```bash
mvn clean compile test-compile
```

### Manual TestNG execution:
```bash
java -cp "target/test-classes;target/classes;%MAVEN_REPOSITORY%\*" org.testng.TestNG testng.xml
```
