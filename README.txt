Assignment 4: Image Processor

Our design follows the Model, View Controller design pattern with a few 
alterations. The javadoc on the classes and methods go into more detail about 
the exact functionality of the code, but a brief overview is as follows:

To represent an actual image in our processor, we made our own data type for an 
Image, which basically holds an ArrayList<ArrayList<Pixel>>. The interface 
for Image had methods for getting a pixel, setting a pixel, cloning  an 
image, and retrieving the dimensions of the image. This also led to us writing 
our own data type to represent a pixel, which is a very primitive class that 
only stores three integer values for red, green and blue, and has methods that 
users can use to retrieve its red, green, and blue values. 


Our Model implements an ImageProcessorModel which extends an 
ImageProcessorModelState. The ImageProcessorModelState is the interface 
containing only methods that allow the user to make observations about the 
code, while the ImageProcessorModel interface contains the rest of the methods 
that allow the user to perform operations on the images. The class that 
implements the ImageProcessorModel interface is simply an implementation of the 
model using several conned function objects to store most of its functionality. 
This means that only the load and save methods were left in the model, which 
was done intentionally so more command objects could be created later on 
without needing to alter the Model interface. 

Since the view element of this assignment was very minimal, our View interface 
and implementation are also very minimal, with only a few methods that print to 
a given spendable object. 

Our controller interface followed a basic controller design, which meant that 
it was very simple with one method that actually ran the program. Our 
implementation again deviated a bit from the traditional design, as a lot of 
the functionality for the controller was stored in CommandFactory function 
objects, which had the purpose of handling input from the user and returning 
the right commands accordingly. 

We had an ImageUtil class to handle random miscellaneous methods that were used 
across multiple classes such as ensureNotNull(Object o), which ensure the 
provided object is not null and throws an IllegalArgumentException otherwise.

For testing we used Mock objects and abstracted our tests for the command 
factories as they were very similar across all of them. The bulk of our testing 
was for the commands themselves and the command factories, since they stored a 
large part of the functionality of the model and controller, respectively.

Documentation for the commands our program accepts:

“load”  <filepath>  <name>: to load an image from the filesystem; “load” must be followed by the
location of the image to be loaded, and then the name the loaded image will be 
saved to.

“save”  <filepath> <name> : to save an image to the filesystem; “save” must be followed by the
location it’s being saved to, and then the name of the image to be saved.

“brighten”  <imageName>  <intensity>  <newName> : to brighten an image;
“brighten” must be followed by the name of the image to be brightened, the 
value by which it needs to be brightened, and the name the brightened image 
will be saved to. 

“darken”  <imageName>  <intensity>  <newName> : to darken an image; “darken”
must be followed by the name of the image to be darkened, the value by which it 
needs to be darkened, and the name the darkened image will be saved to. 

“flipHorizontal”  <imageName>   <newName> : to flip an image on its vertical
axis; "flipHorizontal” must be followed by the name of the image to be flipped
and the name the flipped image will be saved to. 

“flipVertical”  <imageName>   <newName> : to flip an image on its horizontal
axis; “flipVertical” must be followed by the name of the image to be flipped
and the name the flipped image will be saved to. 

“luma”  <imageName>   <newName> : to greyscaled an image by its luma value;
“luma” must be followed by the name of the image to be grayscaled and the name
the grayscaled image will be saved to. 

“intensity”  <imageName>   <newName> : to greyscaled an image by its intensity;
“intensity” must be followed by the name of the image to be grayscaled and the
name the grayscaled image will be saved to. 

“value”  <imageName>   <newName> : to greyscaled an image by its value; “value”
must be followed by the name of the image to be grayscaled and the name the 
grayscaled image will be saved to. 

“greyscaleRed”  <imageName>   <newName> : to greyscaled an image by its red
value; “greyScaleRed” must be followed by the name of the image to be
grayscaled and the name the grayscaled image will be saved to. 

“greyscaleGreen”  <imageName>   <newName> : to greyscaled an image by its green
value; “greyScaleGreen” must be followed by the name of the image to be
grayscaled and the name the grayscaled image will be saved to. 

“greyscaleBlue”  <imageName>   <newName> : to greyscaled an image by its blue
value; gGreyScaleBlue” must be followed by the name of the image to be
grayscaled and the name the grayscaled image will be saved to.

Quitting the program can be accomplished by writing "q" or "quit" in place of any command names
or arguments. These keywords are case-insensitive.

In order to run Script.txt, simply type each line (that doesn't begin with a #) when
the program runs and press ENTER after each line.

The "koala" and "sixbit" directories in the "res" folder are image used for testing. The
"blerner-examples" folder contains example images generated by running our program.

IMAGE CITATIONS:

Koala: assignment 4 canvas page; starter code; converted and cropped to a smaller size
using paint.net so that we could actually submit the project
sixbit: created ourselves using paint.net for use in this project
blerner: https://www.ccs.neu.edu/home/blerner/personal.html, used with permission from
Professor Lerner himself
