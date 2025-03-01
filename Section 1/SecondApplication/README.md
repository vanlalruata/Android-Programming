# Simple Android Form Submission to WhatsApp

### Watch the YouTube Tutorial Here: https://youtu.be/NhmaSarhjLo
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/NhmaSarhjLo/0.jpg)](https://www.youtube.com/watch?v=NhmaSarhjLo)

## Overview
This project demonstrates how to create a simple Android form using native Java in Android Studio. The form allows users to enter their details and send the information to a specified WhatsApp number when they click the Submit button.

No Material Design components are used—this is a beginner-friendly tutorial focusing on form handling and intent-based messaging in Android.

## Features
* Full Name - ```EditText``` input field
* Date of Birth - ```EditText``` input field
* Gender - ```RadioButton``` selection (Male/Female)
* Address - ```EditText``` input field
* Submit Button - Sends the form data to WhatsApp

## Project Structure
```
📦 Simple-Android-Form-WhatsApp
├── 📁 app
│   ├── 📁 src
│   │   ├── 📁 main
│   │   │   ├── 📁 java/com/mzu/secondapplication
│   │   │   │   ├── MainActivity.java  # Main logic for handling form and sending data to WhatsApp
│   │   │   ├── 📁 res
│   │   │   │   ├── 📁 layout
│   │   │   │   │   ├── activity_main.xml  # UI layout of the form
│   │   │   │   ├── 📁 values
│   │   │   │   │   ├── strings.xml  # Strings used in the app
├── AndroidManifest.xml  # Permissions and application setup
├── README.md
```


## How It Works

1. User fills in their Full Name, Date of Birth, Gender, and Address.
2. Clicking the Submit button formats the data into a WhatsApp message.
3. The app opens WhatsApp with a pre-filled message, allowing the user to send it.


## Technologies Used
* Android Studio
* Java (Native Android Development)
* Explicit & Implicit Intents
* ```EditText```, ```RadioButton```, ```Button```


## How to Use
1. Clone/Copy the repository:
   ```bash
    https://github.com/vanlalruata/Android-Programming/tree/main/Section%201/SecondApplication
   ```

2. Open in Android Studio.

3. Modify the WhatsApp number inside ```MainActivity.java```: ```"https://wa.me/918413111111?text=?";```

4. Run the app on an emulator or physical device.

5. Fill in the form and submit to open WhatsApp with the pre-filled message.


## Like This Project?
### If you found this helpful:
* 🌟 Star this repository
* 🔄 Share it with others
* 📩 Subscribe to the YouTube channel for more tutorials