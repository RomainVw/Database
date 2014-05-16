
CHARACTER := RELATION {
  TUPLE{CHARACTERID CHARACTER#("CH1"), NAME NAME("Pierre")},
  TUPLE{CHARACTERID CHARACTER#("CH2"), NAME NAME("Jean")},
  TUPLE{CHARACTERID CHARACTER#("CH3"), NAME NAME("Benjamin")},
  TUPLE{CHARACTERID CHARACTER#("CH4"), NAME NAME("Paul")}
};

ASSOCIATIONNAME := RELATION {
  TUPLE{ASSOCIATIONID ASSOCIATION#("AS1"), NAME "Les petits riens"},
  TUPLE{ASSOCIATIONID ASSOCIATION#("AS2"), NAME "Les scouts"}
};


ASSOCIATION := RELATION {
  TUPLE{CHARACTERID CHARACTER#("CH2"), ASSOCIATIONID ASSOCIATION#("AS1")},
  TUPLE{CHARACTERID CHARACTER#("CH1"), ASSOCIATIONID ASSOCIATION#("AS2")}
};

PLACE := RELATION {
  TUPLE{PLACEID PLACE#("PL1"), PLACENAME NAME("Bruxelles")},
  TUPLE{PLACEID PLACE#("PL2"), PLACENAME NAME("LLN")},
  TUPLE{PLACEID PLACE#("PL3"), PLACENAME NAME("Reaumur")},
  TUPLE{PLACEID PLACE#("PL4"), PLACENAME NAME("Intel room")}
};

ORIGINATES := RELATION {
  TUPLE{CHARACTERID CHARACTER#("CH1"), PLACEID PLACE#("PL1")}
};

BEHAVIOR := RELATION {
  TUPLE{CHARACTERID CHARACTER#("CH2"), BEHAVIOR "melancholic"}
};

PSEUDO := RELATION {
  TUPLE{CALLERID CHARACTER#("CH1"), CALLEDID CHARACTER#("CH2"), PSEUDONYME NAME("Poulpy")},
  TUPLE{CALLERID CHARACTER#("CH1"), CALLEDID CHARACTER#("CH3"), PSEUDONYME NAME("Benji")}
};

RELATIONLIST := RELATION {
  TUPLE{RELATIONID RELATION#("REL1"), RELATIONTYPE "likes"},
  TUPLE{RELATIONID RELATION#("REL2"), RELATIONTYPE "doesn't like"},
  TUPLE{RELATIONID RELATION#("REL3"), RELATIONTYPE "is father of"},
  TUPLE{RELATIONID RELATION#("REL4"), RELATIONTYPE "is son of"}
};


TIMELESSRELATION := RELATION {
  TUPLE{SOURCE CHARACTER#("CH4"), TARGET CHARACTER#("CH1"), RELATIONID RELATION#("REL3")}
};

DATERELATION := RELATION {
  TUPLE{SOURCE CHARACTER#("CH1"), TARGET CHARACTER#("CH4"), RELATIONID RELATION#("REL4"), DATE ENHANCEDDATE(1992,12,28)}
};
 

RANGERELATION := RELATION {
  TUPLE{SOURCE CHARACTER#("CH1"), TARGET CHARACTER#("CH3"), RELATIONID RELATION#("REL1"), START ENHANCEDDATE(2002,12,9), ENDDATE ENHANCEDDATE(2007,7,13)},
  TUPLE{SOURCE CHARACTER#("CH3"), TARGET CHARACTER#("CH1"), RELATIONID RELATION#("REL1"), START ENHANCEDDATE(2003,11,10), ENDDATE ENHANCEDDATE(2009,8,12)},
  TUPLE{SOURCE CHARACTER#("CH2"), TARGET CHARACTER#("CH3"), RELATIONID RELATION#("REL2"), START ENHANCEDDATE(2003,11,10), ENDDATE ENHANCEDDATE(2009,8,12)}
};



BIRTH := RELATION {
  TUPLE{CHARACTERID CHARACTER#("CH1"), BIRTH ENHANCEDDATE(1992,12,28)},
  TUPLE{CHARACTERID CHARACTER#("CH2"), BIRTH ENHANCEDDATE(1992,4,1)},
  TUPLE{CHARACTERID CHARACTER#("CH3"), BIRTH ENHANCEDDATE(1992,6,3)}
};

DEATH := RELATION {
  TUPLE{CHARACTERID CHARACTER#("CH1"), DEATH ENHANCEDDATE(2064,6,15)}
};

EVENTNAME := RELATION {
  TUPLE{EVENTID EVENT#("EV1"), NAME "The beer festival"}
};

ATTENDS := RELATION {
  TUPLE{CHARACTERID CHARACTER#("CH2"), EVENTID EVENT#("EV1")}
};


EVENTDESCRIPTION := RELATION {
  TUPLE{EVENTID EVENT#("EV1"), DESCRIPTION "blablablablablablablabla"}
};


ASSOCIATIONDESCRIPTION := RELATION {
  TUPLE{ASSOCIATIONID ASSOCIATION#("AS1"), DESCRIPTION "Donnez vos vieux vêtements aux plus demunis"}
};


MAP := RELATION {
  TUPLE{MAPID MAP#("MA1"), NUMWIDTH 10, NUMLENGTH 20, WIDTH 5.0, LENGTH 10.0},
  TUPLE{MAPID MAP#("MA2"), NUMWIDTH 10, NUMLENGTH 10, WIDTH 0.1, LENGTH 0.1}
};


MAPPEDPLACE := RELATION {
  TUPLE{PLACEID PLACE#("PL2"), MAPID MAP#("MA1")},
  TUPLE{PLACEID PLACE#("PL3"), MAPID MAP#("MA2")}
};


SUBPLACE := RELATION {
  TUPLE{PLACEID PLACE#("PL3"), SQUAREID 5, MAPID MAP#("MA1")},
  TUPLE{PLACEID PLACE#("PL4"), SQUAREID 10, MAPID MAP#("MA2")}
};



EVENT := RELATION {
  TUPLE{EVENTID EVENT#("EV1"), PLACEID PLACE#("PL2"), BEGINNING ENHANCEDDATE(2014,3,8), ENDDATE ENHANCEDDATE(2014,3,18)}
};
