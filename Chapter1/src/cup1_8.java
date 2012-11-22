/*
Assume that we have a method isSubstring which checks if one word is substring of another word. Write code to check if s2 is a rotation of s1, by calling isSubstring only once. ("waterbottle" is a rotation of "erbottlewat")
Idea: 
(1) cat "erbottlewat" + "erbottlewat" --> "erbottlewaterbottlewat"
(2) call isSubstring("erbottlewaterbottlewat", "waterbottle")

*/