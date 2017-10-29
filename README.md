# SampleProject

This Android App shows you a list of Countries with some basic information about them such as Regional Name of that Country, The Currency, Currency Code and so on. The Country list is shown in a Grid. If you click on a single grid item, it will take you to the Country Details Activity where you will see some basic information about that country.

Description on how to use this App:

1. Launch the App, it will show you a Splash Screen and will take you to the Login Activity. Just enter any value in the place of Email and Password. These information will be stored in the system cache. Once you register, you will be redirected to the country list Activity.

2. It will take atleast 30 seconds to 2 minutes for the country list to load, It mostly depends on the network latency. It is ususally fast on WiFi but might take some time for the list to load on Mobile network. Though I am showing a progress bar while the app tries to connect to the web API, I am not showing any progress dialog while the list is being downloaded.

3. The reason for no progress on download is because the first time the API is hit it lists all the countries which it gets as response and sometimes the Flag is different for the corresponding Country. It adjusts itself after a couple of seconds but while scrolling up or down the GridView refreshes and all the process is performed again. I have commented the progress bar code in the Adapter Class. If the progress bar is needed then you can uncomment it and run the app again.

5. If the lists take too long to load lock the smartphone and unlock it again. You will see the Country List along with their corresponding Flags.
