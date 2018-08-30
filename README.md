# Counterfeit_Coin

The Counterfeit Coin is based on reduce and conquer algorithm. Given (N-1) 
genuine coins and 1 counterfeit coin that looks the same but weights less, 
determine how to detect the counterfeit coin using only a balance scale to 
compare equal numbers of coins in the smallest number of weighings.
 
If the number is even, the two halves are compared and the lighter half 
contains the counterfeit coin. If odd, one of the coins is set aside and 
the even number is divided and compared. If the two halves are equal, the 
coin set aside is counterfeit. Otherwise the counterfeit coin is in the 
lighter half. If only two coins remain, the lighter one is counterfeit.
 
Given a collection of coins represented by string, returns the index of the 
counterfeit coin using only string comparison to represent a balance scale.
