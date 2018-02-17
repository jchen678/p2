#
# SAMPLE FROM:
# https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html
#

JFLAGS = -g
JC = javac
CP = .:/usr/share/java/junit4.jar

.SUFFIXES: .java .class

#
# compiles .java source into .class files
#
.java.class:
	 $(JC) $(JFLAGS) -cp $(CP) $*.java

CLASSES = \
	SearchTreeADT.java \
	BalancedSearchTree.java \
	TestSearchTree.java \

default: classes

classes: $(CLASSES:.java=.class)



#
# make clean - removes all class files and permit new build of all
#
clean:
	$(RM) *.class


#
# make junit -  run the TestSearchTree JUnit test class
#
junit:
	java -cp $(CP) org.junit.runner.JUnitCore TestSearchTree


#
# make copy - use scp to copy files from assignment directory to current directory
#
# CAUTION: this will overwrite existing files with same name in same directory 
#          without warning or chance to cancel copy
# 
# TO USE:
#          remove echo from start of scp command
#          replace cslogin with your CS login username
#
copy:
	echo scp -r cslogin@best-linux.cs.wisc.edu:/p/course/cs400-deppeler/public/html-s/assignments/p2/files/* .
