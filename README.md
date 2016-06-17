# AndroidTest
1. Please write a single view application that needs to send some data (any arbitrary JSON) using HTTP POST to URL1  ONLY using Wifi network. It also needs to send some other data (other JSON) request to URL2 ONLY when it is on Cellular data. [Hint: Broadcast receiver have to be used to find as soon the connection changes to Wifi or cellular data].
2. If the result contains fields latitude, longitude and radius then add a Proximity Alert with the given data. Also write code that if the mobile entered this proximity cell then the app fires a local push notification with a message “Entered”
3. If it gets some results back as a result of the call then it saves it in SQLIte database locally.
