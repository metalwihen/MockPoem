# MockPoem

I wanted to find an easy way to mock the network layer so that I can run UI tests without the need for internet or a dedicated QA server. 

This project has a floating action button, which on click, will start a network request to load random poems. The app has four UI states:
1. **EMPTY**: When the app is opened for the first time
2. **LOADING**: When the button has been clicked and a network request is being made in the background
3. **FAILURE**: When the network request has failed
4. **SUCCESS**: When the network request is successful with a poem to show the user

There are two test classes which runs instrumentation tests for the above UI states (under `androidTest/`)
1. **MockedMainActivityTest**: When the network layer is mocked with MockWebServer 
2. **UnmockedMainActivityTest**: When the actual API calls are made using the internet 

