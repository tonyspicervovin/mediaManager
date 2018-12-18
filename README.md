# mediaManager
This program is a media manager application. It allows for the user to enter, store and edit data about their media collections. 
The program creates a new Object of either type Movie, Book or Game which are all subclasses of Media. 
Currently the objects have the same attributes but it allows there to be further development for each media type in the future.
Media entered must have a name and price, description is optional. 
The data is than entered into a database called media.db under the table media. You will need to update the string url to the
full pathing of the database on your machine. 
To delete am item from the list you select it and click the Delete Selected button.
You can search your database by name and it will return any media that contains the search "To" would return "Tony Hawk"
The search function is not case sensitive.
I think the featuer I would be most proud of is having the option to edit any item in the table and have that be updated in the
database and shown in your table. After editing the program displays all media.
You have the option to edit the media type to anything you want but to be functional with the show all of a specific media 
type buttons they must be set to either "Game" "Book" or "Movie" like the options you are given when you initially add.
You also have buttons that show all your movies games or books.
