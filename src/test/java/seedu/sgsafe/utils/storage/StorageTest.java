package seedu.sgsafe.utils.storage;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.domain.casefiles.type.OthersCase;
import seedu.sgsafe.domain.casefiles.type.financial.BurglaryCase;
import seedu.sgsafe.domain.casefiles.type.financial.ScamCase;
import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;
import seedu.sgsafe.domain.casefiles.type.property.ArsonCase;
import seedu.sgsafe.domain.casefiles.type.property.VandalismCase;
import seedu.sgsafe.domain.casefiles.type.traffic.AccidentCase;
import seedu.sgsafe.domain.casefiles.type.traffic.SpeedingCase;
import seedu.sgsafe.domain.casefiles.type.violent.AssaultCase;
import seedu.sgsafe.domain.casefiles.type.violent.MurderCase;
import seedu.sgsafe.domain.casefiles.type.violent.RobberyCase;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @Test
    public void getCaseFromSaveString_theftCaseValidInputNullValues_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:THEFT" +
                "|title:Shop Theft at Main Street" +
                "|date:14/10/2025" +
                "|info:Stolen electronics from retail shop." +
                "|victim:John Tan" +
                "|officer:Sgt. Lim" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:14/10/2025 10:45:33" +
                "|updated-at:20/10/2025 12:12:10" +
                "|stolen-object:";


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(TheftCase.class, readCase);
        assertEquals(CaseCategory.THEFT, readCase.getCategory());
        assertEquals("Shop Theft at Main Street", readCase.getTitle());
        assertEquals("Stolen electronics from retail shop.", readCase.getInfo());
        assertEquals("John Tan", readCase.getVictim());
        assertEquals("Sgt. Lim", readCase.getOfficer());
        assertEquals("14/10/2025", readCase.getDate().format(dateFormatter));
        assertEquals("14/10/2025 10:45:33", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("20/10/2025 12:12:10", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
        assertNull(((TheftCase) readCase).getStolenObject());
    }

    @Test
    public void getCaseFromSaveString_theftCaseValidInputAllValues_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:THEFT" +
                "|title:Shop Theft at Main Street" +
                "|date:14/10/2025" +
                "|info:Stolen electronics from retail shop." +
                "|victim:John Tan" +
                "|officer:Sgt. Lim" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:14/10/2025 10:45:33" +
                "|updated-at:20/10/2025 12:12:10" +
                "|stolen-object:iPhone";


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(TheftCase.class, readCase);
        assertEquals(CaseCategory.THEFT, readCase.getCategory());
        assertEquals("Shop Theft at Main Street", readCase.getTitle());
        assertEquals("Stolen electronics from retail shop.", readCase.getInfo());
        assertEquals("John Tan", readCase.getVictim());
        assertEquals("Sgt. Lim", readCase.getOfficer());
        assertEquals("14/10/2025", readCase.getDate().format(dateFormatter));
        assertEquals("14/10/2025 10:45:33", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("20/10/2025 12:12:10", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
        assertEquals("iPhone",((TheftCase) readCase).getStolenObject());
    }

    @Test
    public void getCaseFromSaveString_burglaryCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:BURGLARY" +
                "|title:House Break-in at Pine Road" +
                "|date:10/09/2025" +
                "|info:Suspect entered through back door." +
                "|victim:Mary Wong" +
                "|officer:Cpl. Tan" +
                "|is-deleted:0" +
                "|is-open:0" +
                "|created-at:10/09/2025 08:22:15" +
                "|updated-at:11/09/2025 09:45:40";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(BurglaryCase.class, readCase);
        assertEquals(CaseCategory.BURGLARY, readCase.getCategory());
        assertEquals("House Break-in at Pine Road", readCase.getTitle());
        assertEquals("Suspect entered through back door.", readCase.getInfo());
        assertEquals("Mary Wong", readCase.getVictim());
        assertEquals("Cpl. Tan", readCase.getOfficer());
        assertEquals("10/09/2025", readCase.getDate().format(dateFormatter));
        assertEquals("10/09/2025 08:22:15", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("11/09/2025 09:45:40", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertFalse(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_scamCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:SCAM" +
                "|title:Online Investment Scam" +
                "|date:02/10/2025" +
                "|info:Victim transferred money to fake broker." +
                "|victim:David Lim" +
                "|officer:Sgt. Koh" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:02/10/2025 15:33:48" +
                "|updated-at:05/10/2025 09:12:00";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(ScamCase.class, readCase);
        assertEquals(CaseCategory.SCAM, readCase.getCategory());
        assertEquals("Online Investment Scam", readCase.getTitle());
        assertEquals("Victim transferred money to fake broker.", readCase.getInfo());
        assertEquals("David Lim", readCase.getVictim());
        assertEquals("Sgt. Koh", readCase.getOfficer());
        assertEquals("02/10/2025", readCase.getDate().format(dateFormatter));
        assertEquals("02/10/2025 15:33:48", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("05/10/2025 09:12:00", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_arsonCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:ARSON" +
                "|title:Warehouse Fire Incident" +
                "|date:21/08/2025" +
                "|info:Fire suspected to be intentional." +
                "|victim:Lee Logistics Co." +
                "|officer:Insp. Tan" +
                "|is-deleted:0" +
                "|is-open:0" +
                "|created-at:21/08/2025 04:55:00" +
                "|updated-at:25/08/2025 10:30:22";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(ArsonCase.class, readCase);
        assertEquals(CaseCategory.ARSON, readCase.getCategory());
        assertEquals("Warehouse Fire Incident", readCase.getTitle());
        assertEquals("Fire suspected to be intentional.", readCase.getInfo());
        assertEquals("Lee Logistics Co.", readCase.getVictim());
        assertEquals("Insp. Tan", readCase.getOfficer());
        assertEquals("21/08/2025", readCase.getDate().format(dateFormatter));
        assertEquals("21/08/2025 04:55:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("25/08/2025 10:30:22", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertFalse(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_murderCaseValidInputAllFields_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:MURDER" +
                "|title:Homicide at Sunset Park" +
                "|date:18/07/2025" +
                "|info:Body found near the park bench." +
                "|victim:Rachel Ong" +
                "|officer:Det. Chan" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:18/07/2025 06:30:45" +
                "|updated-at:19/07/2025 18:05:12" +
                "|weapon:knife" +
                "|number-of-victims:1";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(MurderCase.class, readCase);
        assertEquals(CaseCategory.MURDER, readCase.getCategory());
        assertEquals("Homicide at Sunset Park", readCase.getTitle());
        assertEquals("Body found near the park bench.", readCase.getInfo());
        assertEquals("Rachel Ong", readCase.getVictim());
        assertEquals("Det. Chan", readCase.getOfficer());
        assertEquals("18/07/2025", readCase.getDate().format(dateFormatter));
        assertEquals("18/07/2025 06:30:45", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("19/07/2025 18:05:12", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
        assertEquals("knife", ((MurderCase) readCase).getWeapon());
        assertEquals(1, ((MurderCase) readCase).getNumberOfVictims());
    }

    @Test
    public void getCaseFromSaveString_accidentCaseValidInputAllValues_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:ACCIDENT" +
                "|title:Car Collision on Highway" +
                "|date:05/09/2025" +
                "|info:Two cars involved, minor injuries." +
                "|victim:Tan Wei" +
                "|officer:Sgt. Neo" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:05/09/2025 14:12:05" +
                "|updated-at:06/09/2025 09:45:33" +
                "|vehicle-type:Car" +
                "|vehicle-plate:SGB1234A" +
                "|road-name:PIE";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(AccidentCase.class, readCase);
        assertEquals(CaseCategory.ACCIDENT, readCase.getCategory());
        assertEquals("Car Collision on Highway", readCase.getTitle());
        assertEquals("Two cars involved, minor injuries.", readCase.getInfo());
        assertEquals("Tan Wei", readCase.getVictim());
        assertEquals("Sgt. Neo", readCase.getOfficer());
        assertEquals("05/09/2025", readCase.getDate().format(dateFormatter));
        assertEquals("05/09/2025 14:12:05", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("06/09/2025 09:45:33", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
        assertEquals("Car", ((AccidentCase) readCase).getVehicleType());
        assertEquals("SGB1234A", ((AccidentCase) readCase).getVehiclePlate());
        assertEquals("PIE", ((AccidentCase) readCase).getRoadName());
    }

    @Test
    public void getCaseFromSaveString_accidentCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:ACCIDENT" +
                "|title:Car Collision on Highway" +
                "|date:05/09/2025" +
                "|info:Two cars involved, minor injuries." +
                "|victim:Tan Wei" +
                "|officer:Sgt. Neo" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:05/09/2025 14:12:05" +
                "|updated-at:06/09/2025 09:45:33" +
                "|vehicle-type:" +
                "|vehicle-plate:" +
                "|road-name:";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(AccidentCase.class, readCase);
        assertEquals(CaseCategory.ACCIDENT, readCase.getCategory());
        assertEquals("Car Collision on Highway", readCase.getTitle());
        assertEquals("Two cars involved, minor injuries.", readCase.getInfo());
        assertEquals("Tan Wei", readCase.getVictim());
        assertEquals("Sgt. Neo", readCase.getOfficer());
        assertEquals("05/09/2025", readCase.getDate().format(dateFormatter));
        assertEquals("05/09/2025 14:12:05", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("06/09/2025 09:45:33", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
        assertNull(((AccidentCase) readCase).getVehicleType());
        assertNull(((AccidentCase) readCase).getRoadName());
        assertNull(((AccidentCase) readCase).getVehiclePlate());
    }

    @Test
    public void getCaseFromSaveString_othersCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:OTHERS" +
                "|title:Miscellaneous Report" +
                "|date:01/11/2025" +
                "|info:Unclassified incident reported." +
                "|victim:Alex Lim" +
                "|officer:Insp. Goh" +
                "|is-deleted:0" +
                "|is-open:0" +
                "|created-at:01/11/2025 11:11:11" +
                "|updated-at:02/11/2025 12:22:22";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(OthersCase.class, readCase);
        assertEquals(CaseCategory.OTHERS, readCase.getCategory());
        assertEquals("Miscellaneous Report", readCase.getTitle());
        assertEquals("Unclassified incident reported.", readCase.getInfo());
        assertEquals("Alex Lim", readCase.getVictim());
        assertEquals("Insp. Goh", readCase.getOfficer());
        assertEquals("01/11/2025", readCase.getDate().format(dateFormatter));
        assertEquals("01/11/2025 11:11:11", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("02/11/2025 12:22:22", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertFalse(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_vandalismCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:VANDALISM" +
                "|title:Graffiti on School Wall" +
                "|date:12/08/2025" +
                "|info:Spray painted walls, minor damage." +
                "|victim:Greenwood High" +
                "|officer:Cpl. Lee" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:12/08/2025 07:00:00" +
                "|updated-at:13/08/2025 10:20:33";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(VandalismCase.class, readCase);
        assertEquals(CaseCategory.VANDALISM, readCase.getCategory());
        assertEquals("Graffiti on School Wall", readCase.getTitle());
        assertEquals("Spray painted walls, minor damage.", readCase.getInfo());
        assertEquals("Greenwood High", readCase.getVictim());
        assertEquals("Cpl. Lee", readCase.getOfficer());
        assertEquals("12/08/2025", readCase.getDate().format(dateFormatter));
        assertEquals("12/08/2025 07:00:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("13/08/2025 10:20:33", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_robberyCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:ROBBERY" +
                "|title:Bank Robbery Downtown" +
                "|date:30/09/2025" +
                "|info:Armed suspects stole cash from bank." +
                "|victim:ABC Bank" +
                "|officer:Det. Ng" +
                "|is-deleted:0" +
                "|is-open:0" +
                "|created-at:30/09/2025 09:45:33" +
                "|updated-at:01/10/2025 08:12:10";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(RobberyCase.class, readCase);
        assertEquals(CaseCategory.ROBBERY, readCase.getCategory());
        assertEquals("Bank Robbery Downtown", readCase.getTitle());
        assertEquals("Armed suspects stole cash from bank.", readCase.getInfo());
        assertEquals("ABC Bank", readCase.getVictim());
        assertEquals("Det. Ng", readCase.getOfficer());
        assertEquals("30/09/2025", readCase.getDate().format(dateFormatter));
        assertEquals("30/09/2025 09:45:33", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("01/10/2025 08:12:10", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertFalse(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_assaultCaseValidInput_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:ASSAULT" +
                "|title:Street Fight Near Mall" +
                "|date:15/10/2025" +
                "|info:Two individuals involved, minor injuries." +
                "|victim:Lim Wei" +
                "|officer:Sgt. Chua" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:15/10/2025 20:15:00" +
                "|updated-at:16/10/2025 09:00:45";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(AssaultCase.class, readCase);
        assertEquals(CaseCategory.ASSAULT, readCase.getCategory());
        assertEquals("Street Fight Near Mall", readCase.getTitle());
        assertEquals("Two individuals involved, minor injuries.", readCase.getInfo());
        assertEquals("Lim Wei", readCase.getVictim());
        assertEquals("Sgt. Chua", readCase.getOfficer());
        assertEquals("15/10/2025", readCase.getDate().format(dateFormatter));
        assertEquals("15/10/2025 20:15:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("16/10/2025 09:00:45", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_speedingCaseValidInputAllValues_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:SPEEDING" +
                "|title:Excessive Speed on Highway" +
                "|date:07/11/2025" +
                "|info:Vehicle caught at 150 km/h." +
                "|victim:Traffic Authority" +
                "|officer:Cpl. Tan" +
                "|is-deleted:0" +
                "|is-open:0" +
                "|created-at:07/11/2025 13:30:00" +
                "|updated-at:08/11/2025 11:15:22" +
                "|vehicle-type:Car" +
                "|vehicle-plate:SGB1234A" +
                "|road-name:PIE" +
                "|speed-limit:90" +
                "|exceeded-speed:60";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(SpeedingCase.class, readCase);
        assertEquals(CaseCategory.SPEEDING, readCase.getCategory());
        assertEquals("Excessive Speed on Highway", readCase.getTitle());
        assertEquals("Vehicle caught at 150 km/h.", readCase.getInfo());
        assertEquals("Traffic Authority", readCase.getVictim());
        assertEquals("Cpl. Tan", readCase.getOfficer());
        assertEquals("07/11/2025", readCase.getDate().format(dateFormatter));
        assertEquals("07/11/2025 13:30:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("08/11/2025 11:15:22", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertFalse(readCase.isOpen());
        assertEquals("Car", ((SpeedingCase) readCase).getVehicleType());
        assertEquals("SGB1234A", ((SpeedingCase) readCase).getVehiclePlate());
        assertEquals("PIE", ((SpeedingCase) readCase).getRoadName());
        assertEquals(90, ((SpeedingCase) readCase).getSpeedLimit());
        assertEquals(60, ((SpeedingCase) readCase).getExceededSpeed());
    }

    @Test
    public void getCaseFromSaveString_speedingCaseValidInputNullValues_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:SPEEDING" +
                "|title:Excessive Speed on Highway" +
                "|date:07/11/2025" +
                "|info:Vehicle caught at 150 km/h." +
                "|victim:Traffic Authority" +
                "|officer:Cpl. Tan" +
                "|is-deleted:0" +
                "|is-open:0" +
                "|created-at:07/11/2025 13:30:00" +
                "|updated-at:08/11/2025 11:15:22" +
                "|vehicle-type:" +
                "|vehicle-plate:" +
                "|road-name:" +
                "|speed-limit:" +
                "|exceeded-speed:";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(SpeedingCase.class, readCase);
        assertEquals(CaseCategory.SPEEDING, readCase.getCategory());
        assertEquals("Excessive Speed on Highway", readCase.getTitle());
        assertEquals("Vehicle caught at 150 km/h.", readCase.getInfo());
        assertEquals("Traffic Authority", readCase.getVictim());
        assertEquals("Cpl. Tan", readCase.getOfficer());
        assertEquals("07/11/2025", readCase.getDate().format(dateFormatter));
        assertEquals("07/11/2025 13:30:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("08/11/2025 11:15:22", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertFalse(readCase.isOpen());
        assertNull(((SpeedingCase) readCase).getVehicleType());
        assertNull(((SpeedingCase) readCase).getVehiclePlate());
        assertNull(((SpeedingCase) readCase).getRoadName());
        assertNull(((SpeedingCase) readCase).getSpeedLimit());
        assertNull(((SpeedingCase) readCase).getExceededSpeed());
    }

    @Test
    public void getCaseFromSaveString_theftCaseMissingVictimOfficer_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:THEFT" +
                "|title:Bag Snatching at Park" +
                "|date:05/10/2025" +
                "|info:Victim bag was snatched but no injuries." +
                "|victim:" +   // missing victim
                "|officer:" +  // missing officer
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:05/10/2025 09:15:00" +
                "|updated-at:05/10/2025 09:45:33" +
                "|stolen-object:Louis Vuitton bag";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(TheftCase.class, readCase);
        assertEquals(CaseCategory.THEFT, readCase.getCategory());
        assertEquals("Bag Snatching at Park", readCase.getTitle());
        assertEquals("Victim bag was snatched but no injuries.", readCase.getInfo());
        assertNull(readCase.getVictim());
        assertNull(readCase.getOfficer());
        assertEquals("05/10/2025", readCase.getDate().format(dateFormatter));
        assertEquals("05/10/2025 09:15:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("05/10/2025 09:45:33", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
        assertEquals("Louis Vuitton bag", ((TheftCase) readCase).getStolenObject());
    }


    @Test
    public void getCaseFromSaveString_burglaryCaseMissingVictimOfficer_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:BURGLARY" +
                "|title:Office Burglary Downtown" +
                "|date:12/09/2025" +
                "|info:Break-in during weekend, electronics stolen." +
                "|victim:" +
                "|officer:" +
                "|is-deleted:0" +
                "|is-open:0" +
                "|created-at:12/09/2025 07:00:00" +
                "|updated-at:13/09/2025 13:22:10";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(BurglaryCase.class, readCase);
        assertEquals(CaseCategory.BURGLARY, readCase.getCategory());
        assertEquals("Office Burglary Downtown", readCase.getTitle());
        assertEquals("Break-in during weekend, electronics stolen.", readCase.getInfo());
        assertNull(readCase.getVictim());
        assertNull(readCase.getOfficer());
        assertEquals("12/09/2025", readCase.getDate().format(dateFormatter));
        assertEquals("12/09/2025 07:00:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("13/09/2025 13:22:10", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertFalse(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_scamCaseMissingVictimOfficer_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:SCAM" +
                "|title:Fake Charity Email Scam" +
                "|date:20/08/2025" +
                "|info:Victim almost transferred money to fraudulent charity." +
                "|victim:" +
                "|officer:" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:20/08/2025 14:30:00" +
                "|updated-at:21/08/2025 16:10:45";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(ScamCase.class, readCase);
        assertEquals(CaseCategory.SCAM, readCase.getCategory());
        assertEquals("Fake Charity Email Scam", readCase.getTitle());
        assertEquals("Victim almost transferred money to fraudulent charity.", readCase.getInfo());
        assertNull(readCase.getVictim());
        assertNull(readCase.getOfficer());
        assertEquals("20/08/2025", readCase.getDate().format(dateFormatter));
        assertEquals("20/08/2025 14:30:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("21/08/2025 16:10:45", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_arsonCaseMissingVictimOfficer_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:ARSON" +
                "|title:Garage Fire at Suburbia" +
                "|date:02/11/2025" +
                "|info:Fire suspected to be deliberately set." +
                "|victim:" +
                "|officer:" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:02/11/2025 21:00:00" +
                "|updated-at:03/11/2025 08:45:12";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(ArsonCase.class, readCase);
        assertEquals(CaseCategory.ARSON, readCase.getCategory());
        assertEquals("Garage Fire at Suburbia", readCase.getTitle());
        assertEquals("Fire suspected to be deliberately set.", readCase.getInfo());
        assertNull(readCase.getVictim());
        assertNull(readCase.getOfficer());
        assertEquals("02/11/2025", readCase.getDate().format(dateFormatter));
        assertEquals("02/11/2025 21:00:00", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("03/11/2025 08:45:12", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
    }

    @Test
    public void getCaseFromSaveString_murderCaseMissingVictimOfficer_successfullyReturnsCase() {
        String saveString = "id:000000" +
                "|category:MURDER" +
                "|title:Suspicious Death in Apartment" +
                "|date:28/08/2025" +
                "|info:Body discovered in apartment, investigation ongoing." +
                "|victim:" +
                "|officer:" +
                "|is-deleted:0" +
                "|is-open:1" +
                "|created-at:28/08/2025 22:22:22" +
                "|updated-at:29/08/2025 09:09:09";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        Storage storage = new Storage(null);

        Case readCase = storage.getCaseFromSaveString(saveString);
        assertInstanceOf(MurderCase.class, readCase);
        assertEquals(CaseCategory.MURDER, readCase.getCategory());
        assertEquals("Suspicious Death in Apartment", readCase.getTitle());
        assertEquals("Body discovered in apartment, investigation ongoing.", readCase.getInfo());
        assertNull(readCase.getVictim());
        assertNull(readCase.getOfficer());
        assertEquals("28/08/2025", readCase.getDate().format(dateFormatter));
        assertEquals("28/08/2025 22:22:22", readCase.getCreatedAt().format(dateTimeFormatter));
        assertEquals("29/08/2025 09:09:09", readCase.getUpdatedAt().format(dateTimeFormatter));
        assertFalse(readCase.isDeleted());
        assertTrue(readCase.isOpen());
    }
}
