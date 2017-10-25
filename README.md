# react-native-background-check
For Android Only

### Content

- [Installation](#installation)
- [Usage](#usage)
- [Example React Native Android App](#example-react-native-android-app)
- [Questions?](#questions)

### Installation

1. Install

2. Link your native dependencies:

  ```
  react-native link react-native-background-check
  ```

3. Compile and have fun!


### Usage

Include the library in your code:

```
import BackgroundCheckModule from 'react-native-background-check';
```

#### APIs:

##### alert(String: message)
Simple test function, call Android toast api.

##### openSettings()
Open Android Setting page.

##### bringApptoBackground()
Bring your app to Background, you'd better check if your app in foreground befroe you call it.

##### bringApptoForeground()
Bring your app to Foreground, you'd better check if your app in background befroe you call it.

##### lightScreen()
Set window flags to light your screen, and bring app over lock screen.

##### clearWindow()
Clear window flags you set in `lightScreen()`



### Example React Native Android App

Working on it...


### Questions?

Feel free to [create an issue](https://github.com/ShongSu/react-native-background-check/issues/new)
