*** Settings ***
Library  SeleniumLibrary
Library  OperatingSystem
Library  Collections
Library  Screenshot
Suite Setup  Initialize dictionary
*** Variables ***
${REMOTEURL}         http://192.168.1.103:4444/wd/hub
${username}         testuser@test.com
${password}         junkpwd
${projectName}         SCRUM (BS)
${SLEEP}         5s
*** Keywords ***
Initialize dictionary
    ${MyDictionary}=    Create Dictionary
    Set Suite Variable  ${MyDictionary}
*** Test Cases ***
#Setup Driver
#	Set Environment Variable  webdriver.gecko.driver    D:\\gecko\\geckodriver.exe
#	Set Environment Variable  webdriver.chrome.driver  	D:\\gecko\\chromedriver.exe 
Browser Settings
    #	${ff default caps}    Evaluate    sys.modules['selenium.webdriver'].common.desired_capabilities.DesiredCapabilities.FIREFOX    sys,selenium.webdriver
    #	Set To Dictionary    ${ff default caps}    marionette=${True}
    #	${executor}    Evaluate    str('${REMOTEURL}')
    #	Create WebDriver    Remote    desired_capabilities=${ff default caps}    command_executor=${executor}
   	${ff default caps}    Evaluate    sys.modules['selenium.webdriver'].common.desired_capabilities.DesiredCapabilities.CHROME    sys,selenium.webdriver
#	${ff default caps}    Evaluate    sys.modules['selenium.webdriver'].common.desired_capabilities.DesiredCapabilities.FIREFOX    sys,selenium.webdriver
	Set To Dictionary    ${ff default caps}    marionette=${True}
	Create WebDriver    Chrome
#	Create WebDriver    Firefox	
    #	Go To    https://stackoverflow.com/users/login
    Go To  https://mail.google.com  
Login Page
    Wait Until Page Contains Element  //input[@id="identifierId"]
    Input Text  //input[@id="identifierId"]  ${username}

    Sleep  ${SLEEP}
    Wait Until Page Contains Element  //span[@class="RveJvd snByac"]
    Click Element  //span[@class="RveJvd snByac"]

    Sleep  ${SLEEP}
    ${value}  Get Text  xpath=(//p)[3]
    Set To Dictionary    ${MyDictionary}    output1    ${value}
    Sleep  ${SLEEP}
    Wait Until Page Contains Element  //div[@class="bdf4dc"]
    Click Element  //div[@class="bdf4dc"]

    Sleep  ${SLEEP}
    Wait Until Page Contains Element  xpath=(//div)[17]
    ${Welcometesttestcom}  Get Text  xpath=(//div)[17]
    Set To Dictionary    ${MyDictionary}    Welcometesttestcom1    ${Welcometesttestcom}  
    Sleep  ${SLEEP}
    #Log To Console  ${value}
    


#    Sleep  ${SLEEP}
#    Wait Until Page Contains Element  //a[@id="log_out"]
#    Click Link  //a[@id="log_out"]

#    Sleep  ${SLEEP}
Log Dictionaries
    Log Variables
	:FOR  ${key}  IN  @{MyDictionary.keys()}
	\    Append To File  ${OUTPUT_DIR}/${__FILE_NAME__}  ${key}:${MyDictionary["${key}"]}
	\    Log  ${key}:${MyDictionary["${key}"]}

Close
    Close Browser