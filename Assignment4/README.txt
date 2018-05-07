We have included some .obj files with data. To use rename the file dataAuxX.obj to data.obj

dataAux1.obj:
(the info is the users 5199911X (host) and 54444111D (multirole))
Contains a booked offer, a paid offer, a banned user, an offer pending for changes, an offer pending for approval and some more general information.
This file is meant to be used as an example of how the app works.

dataAux2.obj:

Contains a offer pending for changes(login as NIF: 51999111X). Execute it to see that the offer exists and then uncomment the line ModifiableDate.plusDays(10) in RunApp to see that
the offer is deleted because the 5-day period is up. 

dataAux3.obj

Contains a booked offer(login as NIF: 54444111D). Again follow the same procedure to see that the reservations that are not paid within 5-days  are deleted and the same user 
cannot book the same offer. Try to search the offer for zip (28032) and try to book it and pay it to see that it is not possible.

It has data.obj from default so it will not have any data charged initially.