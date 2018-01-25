/********************/					
	Read Me
/********************/
  MOVIE APPLICATION
  NOTE: Run the app in API LVL 23.


/*************************/
Instructions/ Installation
/*************************/

		-Extract the folder from rar file using any zip software.
		-Open it with Android Studio.
		-Apk is generated and can be found in this link inside the app folder --> <appname>\app\build\outputs\apk

   
/************************/
Description
/************************/

		-The app consists of two activity named Tabbed Activity and Add/Edit Activity.
		-Tabbed Activity provides two tabs named Most recent and Highest rated both tabs provides the list view to display the Movie Name, Date and Rating created by the user.
		-Most recent displays the list which is sorted by date the user entered in descending order.
		-Highest rated displays the list which is sorted by ratings in descending order.
		-Add/Edit Activity provides the DatePicker Widget, Edittext, Rating Bar, checkbox to notify user to rate movie if it is cehecked and two action buttons on the action bar.
		
		-Buttons are used to provide actionListeners:
			1)Save button saves the values into database.
 			2)Discard button discards the changes.

                
/************************/
DBHelper.class
/***********************/

		-SQLite is used to create a database to store, retrieve and modify the data.
                             
/***********************/
Inputs 
/***********************/

		-Click Icon on action bar of Tabbed Activity to enter new movie.
		-Click Save Icon to save movie data.
		-Click Cancel Icon to discard.

/***************************/
	Notification
/***************************/
		-Notifies the user if they have checked the checkbox and not rated the movie.	


/***********************/
Outputs
/***********************/

		Movie list will be created and displayed in the list view.
		Users will be able to modify the data by clicking on the movie in listview.
		

/*********************/
Developed by
/*********************/

		Varun Krishna
		Email: kvarun94@csu.fullerton.edu