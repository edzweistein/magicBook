magicBook
=========

under construction :) stay tuned we are working to release a nice version of magicBook! :)


Installation
=========

If you just want to use magicBook you can download a prebuild .jar-File.
Dependencies (need to be installed on the working system):
OpenCV (2.4.5 or higher ) and a compatible webcam
VLC (2.0.5 or higher) (which wil be used to playback the media vic the telnet interface -> free software and supports almost every filetype :)
magicBook is designed to be portable and is tested to be running under windows and linux.



Developing
=========

**1.** If you are using **Eclipse** as IDE, you can use these example **.project**. Just create a new file with the following content in the root directory of the project:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>magicBook</name>
	<comment></comment>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>org.eclipse.jdt.core.javabuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
		<buildCommand>
			<name>com.stateofflow.eclipse.metrics.MetricsBuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	<natures>
		<nature>org.eclipse.jdt.core.javanature</nature>
		<nature>com.stateofflow.eclipse.metrics.MetricsNature</nature>
	</natures>
</projectDescription>
```

**2.**
magicBook is using some (open source) third-party libraries. Therefore you have to include the libraries to your Java Build Path.
Create a new file <filename>.userlibraries with the content:

```xml
<?xml version="1.0" encoding="WINDOWS-1252" standalone="no"?>
<eclipse-userlibraries version="2">
    <library name="Apache Commons Telnet" systemlibrary="false">
        <archive path="/magicBook/libs/apache commons net_telnet/commons-net-3.2/commons-net-3.2.jar"/>
        <archive path="/magicBook/libs/apache commons net_telnet/commons-net-3.2/commons-net-3.2-sources.jar"/>
    </library>
    <library name="FileDrop" systemlibrary="false">
        <archive path="/magicBook/libs/filedrop/filedrop.jar"/>
    </library>
    <library name="JavaCV" systemlibrary="false">
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/ffmpeg-1.2-windows-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/javacpp.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/javacv.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/javacv-windows-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/opencv-2.4.5-windows-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/ffmpeg-1.2-linux-x86.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/ffmpeg-1.2-linux-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/ffmpeg-1.2-macosx-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/ffmpeg-1.2-windows-x86.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/javacv-linux-x86.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/javacv-linux-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/javacv-macosx-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/opencv-2.4.5-linux-x86.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/opencv-2.4.5-linux-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/opencv-2.4.5-macosx-x86_64.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/opencv-2.4.5-windows-x86.jar"/>
        <archive path="/magicBook/libs/javacv/javacv-0.5-bin/javacv-bin/javacv-windows-x86.jar"/>
    </library>
    <library name="JMyron" systemlibrary="false">
        <archive path="/magicBook/libs/jmyron/JMyron.jar"/>
    </library>
    <library name="JSpiff" systemlibrary="false">
        <archive path="/magicBook/libs/jspiff/jspiff-1.0/jspiff-1.0.jar"/>
        <archive path="/magicBook/libs/jspiff/dom4j-1.6.1.jar"/>
    </library>
    <library name="JUnit" systemlibrary="false">
        <archive path="/magicBook/libs/junit/hamcrest-core-1.3.jar"/>
        <archive path="/magicBook/libs/junit/junit-4.11.jar"/>
    </library>
    <library name="SQLiteJDBC" systemlibrary="false">
        <archive path="/magicBook/libs/jdbcsqlitedriver/sqlite-jdbc-3.7.8-20111025.014814-1.jar"/>
    </library>
    <library name="XStream" systemlibrary="false">
        <archive path="/magicBook/libs/xstream/xstream-1.4.2/lib/xstream/xmlpull-1.1.3.1.jar"/>
        <archive path="/magicBook/libs/xstream/xstream-1.4.2/lib/xstream/xpp3_min-1.1.4c.jar"/>
        <archive path="/magicBook/libs/xstream/xstream-1.4.2/lib/xstream-1.4.2.jar"/>
    </library>
    <library name="ZXing" systemlibrary="false">
        <archive path="/magicBook/libs/zxing/core.jar"/>
        <archive path="/magicBook/libs/zxing/javase.jar"/>
    </library>
</eclipse-userlibraries>

```

In Window->Preferences->Java->Build Path->User Libraries you can import a prepared set of necessary libraries. After succesfully importing the **User Libraries** definition, you have to navigate to the project specific build path via (right-click on the project in the "Package Explorer") Properties->Java Build Path. Click on the button **Add library** and choose **User Library**. In the dialog choose **Apache Commons Telnet**, **FileDrop**, **JavaCV**, **JMyron**, **JSpiff**. **JUNit**, **SQLiteJDBC**, **XStream** and **ZXing**. Click finish to confirm your choice. Now all required libraries are included into the projects build path.



License
=========

This work (magicBook) is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License. 
To view a copy of this license, 
visit http://creativecommons.org/licenses/by-nc-sa/4.0/ 
or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.


---> Lets start real,non-commercial sharing and working together again! Peace! :)

