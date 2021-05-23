# AppScuole

Welcome Screen: 

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/welcome.png" alt="alt text" width="" height="700">

With this app you can search for an italian school.
You can search by
<ul>
  <li>school name</li>
  <li>Characteristics of the school</li>
  <li>Proximity to the school</li>
</ul>

In the MainActivity we have implemented a NavigationView that allows us to navigate between the various fragments, we have also »set» our SharedPreferences which will allow us to locally save our favorite schools and schools already requested from the external database.

Search by name:

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/fragmentricercapernome.png" alt="alt text" width="" height="700">

In the fragment search by name we have implemented an edittext that with the addTextChangedListener method allows us to query the external database (Through an AsyncTask) in run-time mode by typing only 5 characters. Under the edittext there is a RecyclerView that allows us to view the list of results (we use a Handler to update the list), each row is also a button that allows us to open the required school activity (thanks to the use of Intent).

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/mappa.png" alt="alt text" width="" height="700">

In the fragment Map we display a MapBox map depicting our position and the various Markers, which are none other than Schools close to us within 5 km of air. This fragment also queries an external database that responds to us based on our position. (We always use an AsyncTask to query the db and Handler for updating).
Each Marker, if clicked, presents a View, with the name of the school, the address and a button that allows us to open the requested school activity.


Search by characteristics:

<table cellspacing=”2″ cellpadding=”2″ width=”560″ border=”0″>
<tbody>
<tr>
<td valign=”top” width=”186″><img src="https://github.com/Dvd96/AppScuole/blob/main/Img/caratteristiche.png" alt="alt text" width="" height="700"></td>
<td valign=”top” width=”186″><img src="https://github.com/Dvd96/AppScuole/blob/main/Img/caratteristiche2.png" alt="alt text" width="" height="700">
</td>
</tr></tbody></table>

In this fragment we have the opportunity to search for schools by characteristics:
The Region and the Province are required fields, the others are at the user's choice.
We used the Spinners to manage the user's choice, we use AsyncTask to request an external db and its Handler for updating.
Once the Search button is clicked, it opens a page with a Recycler View (the operation is similar to the Recycler View of Search by Name).



School:

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/scuola.png" alt="alt text" width="" height="700">


In this Activity we find information about the School. Here too we query an external database (if the requested School is not already saved in our SharedPreferences). There is also an "Add to your favorites" button which saves the Schools in your favorites through the SharedPreferences.
School addresses are managed thanks to an ExpandableListView that displays the school address and the subjects taught.


Favorite Fragment:

<img src="https://github.com/Dvd96/AppScuole/blob/main/Img/preferiti.png" alt="alt text" width="" height="700">


In the favorite fragment we have the list of favorite schools, saved thanks to the SharedPreferences, by clicking on it we can open the resulting School Activity. There is also a button that allows us to delete the saved school, both from the RecyclerView and from the SharedPreferences.


