# AppScuole

Welcome Screen: 

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/welcome.png" alt="alt text" width="" height="700">

With this app you can search for an italian school.
You can search for
<ul>
  <li>school name</li>
  <li>Characteristics of the school</li>
  <li>Proximity to the school</li>
</ul>

In the MainActivity we have implemented a NavigationView that allows us to navigate between the various fragments, we have also »set» our SharedPreferences which will allow us to locally save our favorite schools and schools already requested from the external database.

Search for name:

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/fragmentricercapernome.png" alt="alt text" width="" height="700">

In the fragment search by name we have implemented an edittext that with the addTextChangedListener method allows us to query the external database (Through an AsyncTask) in run-time mode by typing only 5 characters. Under the edittext there is a RecyclerView that allows us to view the list of results (we use a Handler to update the list), each row is also a button that allows us to open the required school activity (thanks to the use of Intent).

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/mappa.png" alt="alt text" width="" height="700">

In the fragment Map we display a MapBox map depicting our position and the various Markers, which are none other than Schools close to us within 5 km of air. This fragment also queries an external database that responds to us based on our position. (We always use an AsyncTask to query the db and Handler for updating).
Each Marker, if clicked, presents a View, with the name of the school, the address and a button that allows us to open the requested school activity.

<table cellspacing=”2″ cellpadding=”2″ width=”560″ border=”0″>
<tbody>
<tr>
<td valign=”top” width=”186″><img src="https://github.com/Dvd96/AppScuole/blob/main/Img/caratteristiche.png" alt="alt text" width="" height="700"></td>
<td valign=”top” width=”186″><img src="https://github.com/Dvd96/AppScuole/blob/main/Img/caratteristiche2.png" alt="alt text" width="" height="700">
</td>
</tr></tbody></table>
