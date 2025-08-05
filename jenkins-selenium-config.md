# Jenkins Windows Slave Configuration for Selenium Tests

## Prerequisites for Windows Jenkins Slave

### 1. Java Installation
- Install Java 17 JDK on the Windows slave
- Set JAVA_HOME environment variable
- Add Java to PATH

### 2. Maven Installation
- Install Maven 3.8+ on the Windows slave
- Set MAVEN_HOME environment variable
- Add Maven to PATH

### 3. Browser Installation
- Install Chrome (recommended for headless testing)
- Install Firefox (optional alternative)
- Install Edge (Windows native option)

## Jenkins Job Configuration

### 1. Build Environment
```xml
<!-- In Jenkins job configuration -->
<properties>
  <hudson.model.ParametersDefinitionProperty>
    <parameterDefinitions>
      <hudson.model.StringParameterDefinition>
        <name>browser</name>
        <defaultValue>chrome</defaultValue>
        <description>Browser to use for testing (chrome, firefox, edge)</description>
      </hudson.model.StringParameterDefinition>
      <hudson.model.StringParameterDefinition>
        <name>url</name>
        <defaultValue>http://your-app-url</defaultValue>
        <description>Application URL to test</description>
      </hudson.model.StringParameterDefinition>
      <hudson.model.StringParameterDefinition>
        <name>usr</name>
        <defaultValue>testuser</defaultValue>
        <description>Test username</description>
      </hudson.model.StringParameterDefinition>
      <hudson.model.StringParameterDefinition>
        <name>pass</name>
        <defaultValue>testpass</defaultValue>
        <description>Test password</description>
      </hudson.model.StringParameterDefinition>
      <hudson.model.StringParameterDefinition>
        <name>path</name>
        <defaultValue>C:\\jenkins\\screenshots</defaultValue>
        <description>Screenshot path for test failures</description>
      </hudson.model.StringParameterDefinition>
    </parameterDefinitions>
  </hudson.model.ParametersDefinitionProperty>
</properties>
```

### 2. Build Steps
```bash
# Clean and compile
mvn clean compile test-compile

# Run tests with parameters
mvn test -Dbrowser=%browser% -Durl=%url% -Dusr=%usr% -Dpass=%pass% -Dpath=%path%
```

### 3. Post-build Actions
- Archive test results: `**/target/surefire-reports/*.xml`
- Archive screenshots: `**/screenshots/*.png`
- Publish TestNG results: `**/target/surefire-reports/testng-results.xml`

## Windows-Specific Considerations

### 1. Path Separators
- Use `\\` for Windows paths in Jenkins parameters
- The updated baseDriver.java handles cross-platform paths automatically

### 2. Headless Mode
- Tests run in headless mode by default for Jenkins
- Can be disabled by removing `--headless` argument in baseDriver.java

### 3. Screen Resolution
- Set to 1920x1080 for consistent screenshots
- Adjust in ChromeOptions if needed

### 4. Permissions
- Ensure Jenkins service has permission to create directories
- Screenshot directory should be writable

## Troubleshooting

### Common Issues:
1. **Driver not found**: WebDriverManager handles this automatically
2. **Permission denied**: Check Jenkins service permissions
3. **Display issues**: Use headless mode with proper window size
4. **Path issues**: Use forward slashes in Java code, backslashes in Jenkins params

### Debug Commands:
```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Test compilation only
mvn clean compile test-compile

# Run single test
mvn test -Dtest=VProfile_TestSuite
```
