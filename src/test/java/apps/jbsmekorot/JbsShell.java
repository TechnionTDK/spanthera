package apps.jbsmekorot;

import org.junit.*;
import jngram.NgramDocument;

import java.util.List;

/**
 * It is called "Shell" since we put here operations that simulate a shell
 * for exploration of the index and the algorithm. Perhaps one day
 * we will have a real shell...
 * Created by omishali on 03/10/2017.
 */
public class JbsShell {
    private JbsTanachIndex index;
    NgramDocument doc;
    private String textOrchotTzadikim = "האמת. הנשמה נבראת ממקום רוח הקודש, שנאמר (בראשית ב ד): \"ויפח באפיו נשמת חיים\"; ונחצבה ממקום טהרה, ונבראת מזוהר העליון מכסא הכבוד. ואין למעלה במקום קודשי הקודשים שקר, אלא הכל אמת, שנאמר (ירמיהו י י): \"ויי אלהים אמת\". ומצאתי כי כתיב: \"אהיה אשר אהיה\" (שמות ג יד), וכתיב: \"ויי אלהים אמת, הוא אלהים חיים ומלך עולם\" (ירמיהו שם). ועתה יש להודיעך שהקדוש ברוך הוא אלהים אמת: כי תמצא עשרים ואחת פעמים \"אהיה\" שהוא בגימטריא \"אמת\", וגם כן \"אהיה\" בגימטריא עשרים ואחת.";
    private String textMidrashRabaEster_7_9 = "וירא המן כי אין מרדכי כרע ומשתחוה לו (אסתר ג, ה), אמר רבי איבו (תהלים סט, כד): תחשכנה עיניהם של רשעים מראות. לפי שמראית עיניהם של רשעים מורידות אותם לגיהנם, הדא הוא דכתיב (בראשית ו, ב): ויראו בני האלהים את בנות האדם. (בראשית ט, כב): וירא חם אבי כנען. (בראשית כח, ח): וירא עשו כי רעות בנות כנען. (במדבר כב, ב): וירא בלק בן צפור. (במדבר כד, א): וירא בלעם כי טוב בעיני ה' לברך את ישראל. וירא המן כי אין מרדכי כרע ומשתחוה לו. אבל מראית עיניהם של צדיקים תואר, לפי שמראית עיניהם של צדיקים מעלה אותם למעלה העליונה, הדא הוא דכתיב (בראשית יח, ב): וישא עיניו וירא והנה שלשה אנשים. (בראשית כב, יג): וירא והנה איל. (בראשית כט, ב): וירא והנה באר בשדה. (שמות ג, ב): וירא והנה הסנה. (במדבר כה, ז): וירא פינחס, לפיכך הם שמחים במראית עיניהם, שנאמר (תהלים קז, מב): יראו ישרים וישמחו.";
    private String textRashi_1_6_9 = "רש\"י אלה תולדת נח נח איש צדיק. הואיל והזכירו, סיפר בשבחו, (א) שנאמר זכר צדיק לברכה (משלי י, ז.). דבר אחר, ללמדך שעיקר תולדותיהם (ב) של צדיקים מעשים טובים בדורותיו. יש מרבותינו דורשים אותו לשבח, כל שכן שאילו היה בדור צדיקים היה צדיק יותר, ויש שדורשים אותו לגנאי, (ג) לפי דורו היה צדיק, ואילו היה בדורו של אברהם לא היה נחשב לכלום (סנהדרין קח.): את האלהים התהלך נח. ובאברהם (ד) הוא אומר אשר התהלכתי לפניו (בראשית כד, מ.), נח היה צריך סעד לתומכו, אבל אברהם היה מתחזק ומהלך בצדקו מאליו: התהלך. לשון עבר, (ה) וזהו שמושו של ל' בלשון (ו) כבד משמשת להבא ולשעבר בלשון אחד, קום התהלך (שם יג, יז.), להבא. התהלך נח, לשעבר. התפלל בעד עבדיך (שמואל-א יב, יט.), להבא. ובא והתפלל אל הבית הזה (מלכים-א ח, מב.), לשון עבר, אלא שהוי\"ו שבראשו (ז) הופכו להבא:";
    private String textMitzva_1_1 = "היא הצווי אשר צונו בהאמנת האלהות, והוא שנאמין שיש שם עלה וסבה הוא פועל לכל הנמצאים, והוא אמרו (שמות כ-ב) ''אנכי ה' אלהיך''. ובסוף גמרא מכות (גמרא מכות כג-ב) אמרו תרי''ג מצות נאמרו למשה בסיני, מאי קראה (דברים לג-ד) ''תורה צוה לנו משה'', ר''ל מנין תור''ה. והקשו על זה ואמרו תורת בגימטריא תרי''א הוי, והיה המענה אנכי ולא יהיה מפי הגבורה שמענום. הנה נתבאר לך שאנכי ה' מכלל תרי''ג מצות, והוא צווי באמונת האלהות כמו שבארנו.";
    private String textRaba_39_9 = "אמר רבי לוי שתי פעמים כתיב לך לך, ואין אנו יודעים אי זו חביבה אם השניה אם הראשונה, ממה דכתיב (בראשית כב, ב): אל ארץ המוריה, הוי השניה חביבה מן הראשונה. אמר רבי יוחנן לך לך מארצך, מארפכי שלך. וממולדתך, זו שכונתך. ומבית אביך, זו בית אביך. אל הארץ אשר אראך, ולמה לא גלה לו, כדי לחבבה בעיניו ולתן לו שכר על כל פסיעה ופסיעה, הוא דעתיה דרבי יוחנן, דאמר רבי יוחנן (בראשית כב, ב): ויאמר קח נא את בנך את יחידך, אמר לו זה יחיד לאמו וזה יחיד לאמו. אמר לו אשר אהבת, אמר לו ואית תחומין במעיא. אמר לו את יצחק, ולמה לא גלה לו, כדי לחבבו בעיניו ולתן לו שכר על כל דבור ודבור, דאמר רב הונא משם רבי אליעזר בנו של רבי יוסי הגלילי, משהה הקדוש ברוך הוא ומתלא עיניהם של צדיקים, ואחר כך הוא מגלה להם טעמו של דבר. כך אל הארץ אשר אראך. על אחד ההרים אשר אמר אליך. (יונה ג, ב): וקרא אליה את הקריאה אשר אני דבר אליך. (יחזקאל ג, כב): קום צא אל הבקעה ושם אדבר אותך.";
    private String textRaba_39_1 = "ויאמר ה' אל אברם לך לך מארצך וגו' (בראשית יב, א), רבי יצחק פתח (תהלים מה, יא): שמעי בת וראי והטי אזנך ושכחי עמך ובית אביך, אמר רבי יצחק משל לאחד שהיה עובר ממקום למקום, וראה בירה אחת דולקת, אמר תאמר שהבירה הזו בלא מנהיג, הציץ עליו בעל הבירה, אמר לו אני הוא בעל הבירה. כך לפי שהיה אבינו אברהם אומר תאמר שהעולם הזה בלא מנהיג, הציץ עליו הקדוש ברוך הוא ואמר לו אני הוא בעל העולם. (תהלים מה, יב): ויתאו המלך יפיך כי הוא אדניך. ויתאו המלך יפיך, ליפותך בעולם, (תהלים מה, יב): והשתחוי לו, הוי ויאמר ה' אל אברם.";
    private String textRaba_49_9 = "חללה לך (בראשית יח, כה), אמר רבי יודן חללה הוא לך בריה הוא לך. אמר רבי אחא חללה חללה שתי פעמים, חלול שם שמים יש בדבר. אמר רבי אבא מעשת דבר אין כתיב כאן, אלא מעשת כדבר, לא היא ולא דכותה, ולא דפחותה מנה. אמר רבי לוי שני בני אדם אמרו דבר אחד, אברהם ואיוב, אברהם אמר חללה לך מעשת כדבר הזה להמית צדיק עם רשע. איוב אמר (איוב ט, כב): אחת היא על כן אמרתי תם ורשע הוא מכלה, אברהם נטל עליה שכר, איוב נענש עליה. אברהם אמר בשולה, איוב אמר פגה, אחת היא על כן אמרתי תם ורשע הוא מכלה. רבי חיא בר אבא אמר ערבובי שאלות יש כאן, אברהם אמר: חללה לך מעשת כדבר הזה להמית צדיק עם רשע, והקדוש ברוך הוא אומר: והיה כצדיק כרשע, יתלה לרשעים בשביל צדיקים...";
    private String textGevurot_8 = "...והסכימו הכל שאברהם לא יפה עשה במה שאמר במה אדע, כי היו רואים שאם נפרש במה אדע באיזה זכות יירש את הארץ, אין התשובה קח לי עגלה משולשת ועז משולשת ואיל משולש יתפרש לפי פשוטו על זאת השאלה, שהוקשה להם למה יירשו את הארץ בזכות הקרבנות והלא לא על דבר זבח צויתי את אבותיכם. ועוד לא מצאנו זה בכתוב שתהא הארץ לישראל כי אם בזכות האבות והשבועה שנשבע להם. ומפני זה פירש רש\"י במה אדע כי אירשנה באיזה זכות יתקיימו ולא יגלו ממנה. אלא שהקשו עליו כי לא יסבול זה לשון הכתוב במה אדע כי אירשנה, דהלשון משמע לשון ירושה דהיינו תחלת הירושה לא הקיום בה: ובפרק בני העיר (מגילה ל\"א ע\"ב) אמר רבי יוסי אלמלא מעמדות לא נתקיימו שמים וארץ, שנאמר ויאמר ה' אלקים במה אדע כי אירשנה אמר אברהם לפני הקדוש ברוך הוא רבונו של עולם שמא בני יהיו חוטאים ואתה עושה להם כאנשי דור המבול ודור הפלגה אמר לו לאו, במה אדע אמר לו קח נא לי עגלה משולשת.";
    private String textGevurot_8_long = "אחר שכבר אמרנו מענין הזכות לאברהם באמונה אשר האמין בה' כאשר אמר לו הקדוש ברוך הוא על הזרע, קשה למה לא האמין כאשר אמר לו על נתינת הארץ, והיה ראוי שיהיה יוצא שכרו בהפסדו. ובמדרש רבות בפרשת לך לך (פ' מ\"ד) ר' יודא בר חנינא אומר לא כקורא תגר אלא אמר לו באיזה זכות אמר לו בזכות כפרות שאני נותן לך לבניך ויאמר אליו קחה לי עגלה משולשת וגו', הראה לו ג' מיני פרים וג' מיני שעירים וג' מיני אילים, ג' מיני פרים פר יום הכפורים ופר הבא על כל המצות ועגלה ערופה, וג' מיני שעירים שעירי הרגלים שעיר ראש חדש שעיר של יחיד, ג' מיני אילים אשם ודאי ואשם תלוי וכבשה של יחיד, ותור וגוזל ותור ובן יונה, ויקח לו את כל אלה רבי שמעון בן יוחאי ורבנן רבי שמעון בן יוחאי אומר כל הכפרות הראה לו ועשירית האיפה לא הראה לו, ורבנן אמרי אף עשירית האיפה הראה לו נאמר כאן ויקח לו כל אלה ונאמר להלן והבאת את המנחה אשר יעשה לו מאלה, ואת הצפור לא בתר הראה לו שמבדילין בעולת עוף ואין מבדילין בחטאת העוף ע\"כ. ומפני שנראה פירוש זה ומדרש זה רחוק מפשט הכתוב דחו את הפירוש הזה בשתי ידים, והסכימו הכל שאברהם לא יפה עשה במה שאמר במה אדע, כי היו רואים שאם נפרש במה אדע באיזה זכות יירש את הארץ, אין התשובה קח לי עגלה משולשת ועז משולשת ואיל משולש יתפרש לפי פשוטו על זאת השאלה, שהוקשה להם למה יירשו את הארץ בזכות הקרבנות והלא לא על דבר זבח צויתי את אבותיכם. ועוד לא מצאנו זה בכתוב שתהא הארץ לישראל כי אם בזכות האבות והשבועה שנשבע להם. ומפני זה פירש רש\"י במה אדע כי אירשנה באיזה זכות יתקיימו ולא יגלו ממנה. אלא שהקשו עליו כי לא יסבול זה לשון הכתוב במה אדע כי אירשנה, דהלשון משמע לשון ירושה דהיינו תחלת הירושה לא הקיום בה: ובפרק בני העיר (מגילה ל\"א ע\"ב) אמר רבי יוסי אלמלא מעמדות לא נתקיימו שמים וארץ, שנאמר ויאמר ה' אלקים במה אדע כי אירשנה אמר אברהם לפני הקדוש ברוך הוא רבונו של עולם שמא בני יהיו חוטאים ואתה עושה להם כאנשי דור המבול ודור הפלגה אמר לו לאו, במה אדע אמר לו קח נא לי עגלה משולשת, אמר לו תינח בזמן שבית המקדש קיים בזמן שאין בית המקדש קיים מה תהא עליהם אמר להם כבר תקנתי להם סדר קרבנות שבזמן שקוראים בהם מעלה אני עליהם כאלו הקריבו לפני קרבן ואני מוחל להם ע\"כ. הרי שהם בארו כי שאלת אברהם היה במה אדע שלא יעבור זרעו מן העולם, לא במה אדע שיתקיימו בניו בארץ. ויש לדקדק על דברי רבותינו ז\"ל הרי הכתוב אומר במה אדע כי אירשנה, ואין נזכר בכתוב במה אדע שלא יעשה להם כאשר עשה לדור המבול ולדור הפלגה: ונראה כי פירוש במה אדע כי אירשנה, כלומר שתהיה הארץ לי לירושה לאחוזת עולם, ולא שהיה מסופק שמא יגלו ממנה דאף אם יגלו ממנה הארץ ירושה להם, דקיימא לן (יבמות פ\"ב ע\"ב) ירושה ראשונה ושנייה יש להם וירושה שלישית אין להם, רק היה ירא שמא יעבור זרעו חס ושלום מן העולם לגמרי ואז תתבטל הירושה לגמרי, ולפיכך השיב לו בזכות הקרבנות. וביאור ענין זה כאשר תעמיק ותבין התשובה אשר השיב לו הקדוש ברוך הוא לאברהם, כי ישראל יש להם מעלה מיוחדת, כי ישראל מעלתם שהם נבדלים מן הפחיתות לגמרי, והחטא שמקבלים אין זה רק מקרה ודבר שהוא מקרה בלבד אפשר הסתלקות. ולפיכך אין ראוי להעביר ישראל בשביל החטא כיון שבעצם הם טהורים ואין החטא להם בעצם והוא דבר מקרה ואין דבר שהוא במקרה מבטל עצם ישראל. והדבר הזה יתבאר לך באריכות עוד בספר הזה בסופו (פרק ס\"ב) ובספר הנצח בעז\"ה. אבל בני אדם שפחיתות להם בעצם, כאשר הם מוסיפים פחיתות מן החטא, אין הפחיתות הזה מקרה שקבל העצם, שהרי פחיתות יש להם בעצם, ואין זה רק הוספה על הפחיתות שיש להם בעצם כשגובר החטא מעבירה. ולפיכך אמר בשביל הכפרות שיש כפרה לישראל על חטאם, ודבר זה הוא לישראל בפרט לא לשום אומה. ובשביל כך יתקיימו ואין העברה להם מן העולם כמו שיש לאומות, שאם היה החטא לישראל בעצם לא היה כפרה להם על ידי קרבן, אך בשביל שהחטא בישראל ענין מקרה כמו שהתבאר, לכך יש להם קרבנות. ובזמן שאין בית המקדש קיים סדר הקרבנות הסרת החטא. והוא דבר נפלא איך סדר הקרבנות עומד במקום הקרבן, כי אין קרבן זולת הקירוב והדבוק בו יתברך, לצאת מן החטא לשוב אליו יתברך, וזהו לשון קרבן התקרבות אל השם יתברך על ידי הקרבן. והנה סדר הקרבנות שהוא ד\"ת שמסדר בד\"ת ענין הקרבנות הוא הדבוק בעצמו, כי אין דבר חבור ודבוק יותר כמו התורה שהוא דבוק וחבור האדם אל בוראו. ודוקא סדר הקרבנות במה שהקרבן בעצמו הוא התדבקות אל השם יתברך, והנה עומד סדר הקרבנות במקום הקרבן עצמו כאשר תדע ותבין. ואם באנו לפרש דבר זה היה ארוך מאוד, כלל הדבר כי תבא השאלה והתשובה על נכון בענין זה, והוא דעת רבותינו ז\"ל במסכת מגילה בפרק בני העיר: אמנם דעת רבותינו ז\"ל במדרש אשר אמרנו למעלה, שאמרו לא כקורא תגר אלא באיזה זכות, לא משמע שכך פירושו, רק כמשמעו שעל עיקר הנתינה שאמר הקדוש ברוך הוא לאברהם ליתן לו הארץ על זה שאל באיזה זכות. וגם זה כשתעיין בדבר זה היטב תבין שהוא נכון מאוד. ויש לך לדעת כי הארץ אשר נתן הקדוש ברוך הוא לאברהם היא ארץ קדושה נבדלת מכל האדמה, ולפיכך כאשר הבטיח הקדוש ברוך הוא על הנתינה רצה לדעת באיזה זכות יירשו הארץ הזאת הקדושה. ולפיכך השיב לו מזה הצד יירשו הארץ בזכות הכפרות, כאשר תדע סוד הכפרות אשר נתן הקדוש ברוך הוא לישראל מורה על שישראל קדושים בעצמם נבדלים מן הפחיתות, ואל\"כ כאשר הוסיפו על הפחיתות לא היה אפשר בהם כפרה, עכשיו שהם בעצמם נבדלים מכל הפחיתות, ראוי להם הכפרה שהחטא אין מתייחס להם ועיקר עצמם בלא חטא לכך ראוים אל הכפרה, לטהר את עצמם מן החטאים אשר אין ראוי לישראל. וכמו שהארץ הקדושה מצד קדושתה אינה סובלת החטא כמו שאמר (ויקרא י\"ח) ולא תקיא הארץ אתכם כשתטמאו ח\"ו הארץ, כך ישראל מצד קדושתם אינם מסוגלים אל החטאים. ולפיכך הארץ מתייחס לישראל בענין זה כי הארץ קדושה במעלתה הנבדלת מן הפחיתות ומן התיעוב וכן ישראל. ודומה למי שבעצמו יפה וטהור שאם נתלכלך בטיט חוזר מיד לטהרתו לפי שבעצמו הוא טהור, ומי שהוא מזוהם ומאוס אין הלכלוך יוצא ממנו לעולם לפי שנוסף לכלוך על הלכלוך, לכך הכפרות שיש לישראל הוא מורה על עצם מעלה הנבדלת שיש לישראל שבאותו מעלה יש להם כפרת החטא. ולפיכך בזכות זה יירשו הארץ הקדושה, כי לפי הענין האדם יש לו מקום לכך כשנתן הקדוש ברוך הוא הארץ הקדושה לישראל בודאי מפני שהם ראוים לה, וכן לכל אומה ואומה נתן ארץ כפי מה שהם, וכל דבר יש לו מקום לפי טבעו ומעלתו, ולכך לפי מעלת ישראל שיש להם מעלה נבדלת קדושה יש להם ארץ קדושה: והבן מחלוקת רבי שמעון בן יוחאי ורבנן, כי לרבי שמעון בן יוחאי לא הראה לו עשירית האיפה, כי עשירית האיפה הוא קרבן עני כשחוטא וזה אין כפרה שלימה כי אין קרבן זה לכלל ישראל אלא לעני בפרט, ומפני שקרבן זה קרבן עני, והרמז בענין הקרבנות על עילוי מעלת ישראל שמפני כך זכו ישראל לרשת הארץ, לא הראה לו כפרת עשירית האיפה שאין זה מעלת ישראל. אבל רבנן סבירי להו דגם עשירית האיפה הראה לו, כי אף כפרה זאת היא משלמת את כלל הקרבנות ולא יחסרו דבר מן הקרבנות, ולכך הראה לו גם כן את עשירית האיפה כדי להשלים הכפרות. והכפרות הם ט' מינים בהמות, מפני שהחטאים דומים לחולי הגוף, והחולי אשר נתהוה בגוף יש חולה שיש לו חמימות יותר מן הראוי, ורפואתו הוא בענין אשר ראוי לו, ויש מקרירות ורפואתו בענין הראוי לזה, ויש בענין הלחות ויש בענין היבשות ורפואתו בענין הראוי לו, כך רפואת הנפש אין זה כזה ולכל אחד קבעה התורה התמימה כפרה ורפואה מה שראוי לו, ולכך הם תשעה כפרות מן הבהמות ובט' יכללו כל החלקים, ותור וגוזל הם אחד כי כן כתיב ואת הצפור לא בתר, כלל שניהם בלשון אחד ואין חילוק בתורה בין תור וגוזל שיכול להביא תור או גוזל הרי הם עשרה קרבנות. כי כבר אמרנו לך כי השם יתברך נתן לישראל כפרות הרבה שאין חטא זה כזה, ומפני כי המספר עולה עד עשרה, שהרי אחד עשרה יחזור למנות אחד דהיינו אחד עשר אם כן אין מספר יותר מעשרה. והטעם בזה לפי שכל דבר בעולם יתחלק לתשעה, לפי שכל דבר אשר אפשר בו חלוק יש בו ראש ואמצע וסוף ולדבר זה יתחלק ודבר זה ידוע. ובענין זה תמצא בו ט' חלקים לכל צד, שכאשר תניח דבר שיש בו חלוקה בכל צד, לא כמו הקו אשר אין בו חלוקה רק באורך ולא ברוחב, אבל דבר שיש בו חלוקה באורך וברוחב עולה מספרו ט', ומזה נדע שכל מספר לא יעלה יותר רק עד ט', שכל זמן שאין במספר יותר מט' נקראים חלקים, שכל אחד ואחד מחולק לעצמו, שהרי יש לכל אחד ואחד צד בפני עצמו ולכך הם חלקים, אבל כאשר יש עוד אחד ששוב לא תוכל לחלקם שיהיה כל אחד ואחד בפני עצמו אז הם כלל אחד ואינם חלקים לעצמם, ולכך ט' מספר החלקים אבל כאשר הם עשרה יחזרו להיות כלל אחד. ולפיכך אמר עגלה משולשת ואיל משולש ועז משולשת שיהיה כל מין משולש, שהרי הט' חלקים משולשים הם והם ט' מיני בהמות, אך העשירי תור או גוזל ענין מיוחד כי העשירי על ידו נשלם המספר ונעשה כלל, ולפיכך היה העשירי דבר בפני עצמו אינו דומה לחלקים. ורז\"ל אמרו שהראה לו שאין מבדילין בחטאת עוף, ביאור ענין זה כי העוף אינו כל כך חשוב לכך לא היה מחלק אותו כמו הבהמה, ובזה הראה לו כי חטאת בהמה שהבהמה חשובה יש בה חלקים ולא חטאת עוף, אבל עולה לחשיבת הקרבן היה בו חלקים כי העולה היא כולה כליל, והתבאר התשובה לאברהם בענין הקרבנות שלא יעביר זרעו כמו שהתבאר: ועוד יש לומר שהארץ נתן השם יתברך לאברהם לירושה נצחית, והיה ירא אברהם שמא יעביר את זרעו חס ושלום כמו שהעביר אומות אחרות ולא יהיה לו הירושה. אז אמר לו בזכות הקרבנות שהקרבן הוא מדבק ישראל אל השם יתברך, ואז נאמר (דברים ד') ואתם הדבקים בה' אלקיכם חיים כלכם היום, שהם גם כן יהיו נצחים כמו שהוא יתב' נצחי ודבר זה יתבאר בסוף הספר (פרק ס\"ט) באריכות. ובמסכת תענית (כ\"ז ע\"ב) אלמלא מעמדות לא נתקיימו שמים וארץ וכו'. וזה מפני כי אין לעולם קיום רק על ידי הדבוק בו יתברך ובו מתקיים הכל, ולפיכך בזכות הקרבנות שמים וארץ מתקיימים וזה יתבאר במקומו. ולכך היה מחתך הבהמות כי חתוך הבהמות דומה בדבר מה להקרבה שההקרבה הוא חתוך שלהם, והקרבת הקרבן גורם שהשם יתברך גוזר ונותן הארץ לאברהם בגזירת ברית, וכאשר עבר בין הגזרים הרי זה עשיית גזירה וכריתה שהרי עבר בין הגזרים ובזה נתן לאברהם הארץ בגזירת ברית, ופירוש זה פי' נכבד מאוד כאשר תבין ותעמיק בסוד הקרבנות: וירד העיט וישב אותם אברם (בראשית ט\"ו) מזה למדו שצריכין ישראל לעשות מעמדות לקרבנות. והטעם כדי שיהיה הקרבן מיוחד לו ולא ישתתף אחר עמו, ואם לא היו שומרים הקרבן היה נראה שהיה אפשר שיהנה אחר מן הקרבן ואין הדבר הזה ראוי. משל מי שעשה סעודה למלך אין מזמן אחר עמו, שיהיה נראה שהסעודה היא למלך בפרט, ולפיכך וירד העיט על הפגרים וישב אותם אברהם שהיה עומד ומשמר שלא יהיה חלק אחר בעבודה, והתבאר דעת רבותינו ז\"ל במדרש רבה, על דעת מי שאמר שלא שאל אברהם אות. אמנם שם תמצא גם כן שיש מרבותינו ז\"ל שאמרו ששאל אות על הירושה וכמו שיתבאר לקמן. ושאלה הראשונה במקומה עומדת, למה שאל אברהם אות על הירושה ולא שאל על הבנים. והנראה בזה כי הבנים גם שאר בני אדם זוכים אל בנים ואין זה שינוי ודבר חדוש בשום ענין, אף להיות זרעו ככוכבי השמים שאין זה רק תוספת ולא נקרא זה מתנה, ואף על גב דנס גדול היה זה שהרי לפי המזל לא היו לו בנים, מכל מקום אין זה מתנה נחשב כיון שכל אדם בטבעו יש לו בנים, ועתה שהבטיחו על בנים לא הבטיח רק שיהא כמו שאר בני אדם ולכך לא שאל אות. אבל על נתינת הארץ צריך ברית ואות דבר שהוא מתנה כמו הארץ שנתן לו הארץ במתנה שהוא ענין מחוץ צריך קיום, שהאומר לאחד אתן לך דבר זה כל זמן שלא זכה באותו דבר אינו מתנה ולכך שאל אות: ובמדרש רבה בפרשת לך לך (פ' מ\"ד) עגלה משולשת זה בבל שהעמידה ג' מלכים נבוכדנצר ואויל מרודך ובלשצר, עז משולשת זה מדי שהיא העמידה ג' מלכים כורש דריוש ואחשורוש, ואיל משולש זה יון, רבי אליעזר ור\"י רבי אליעזר אומר כל הרוחות כבשו בני יון ורוח מזרחית לא כבשו אמר רבי יוחנן והכתיב וראיתי את האיל והנה מנגח ימה וצפונה ונגבה, הוא דעתו דרבי אליעזר שלא אמר מזרחה, תור וגוזל זו אדום תור הוא אלא שגזלן הוא, דבר אחר ויקח את כל אלה רבי יהודה ורבי נחמיה, רבי יהודה אומר שרי אומות העולם הראה לו, ורבי נחמיה אומר שרי ישראל הראה לו כו' ששם סנהדרין גדולה של ישראל יושבת וחותמת דיניהן של ישראל, ואת הצפור לא בתר רבי אבא בר כהנא בשם רבי לוי אמר הראה לו הקדוש ברוך הוא כל מי שמעמיד פנים בגל הגל שוטפו וכל מי שאינו מעמיד פנים בגל אין הגל שוטפו וירד העיט על הפגרים נסב אברהם מכיש והיה מכיש להו ולא הוו מכתשין אף על פי כן וישב אותם אברם בתשובה אמר רבי עזריה לכשיעשו בניך פגרים בלא גידים ועצמות זכותך עומדת להם ע\"כ: העמיקו חכמים בענין זה מאוד, כאשר תדע כי האומות הם נכללים בד' ראשים כוללים כלל האומות הקדמונים לא ראי זה כראי זה, וערך ויחוס האומות אל אברהם כמו ערך הבעלי חיים שאינם מדברים אל הצורה השלימה היא צורת האדם, וכבר התבאר זה למעלה. לכך כאשר כרת ברית עם אברהם, צוה ליקח נגד כללי האומות נגד ד' מלכיות שהם ראשי האומות, ולחלק אותם בתוך, שזה יורה על שהאומות המתנגדות אל הצורה השלימה הם זרע אברהם, והם באים לבטל הברית שיש להקדוש ברוך עם אברהם ועם זרעו מצד התנגדות. לכך צוה לגזור אותם לשנים ועבר השם יתברך בין הגזרים לבטל כח המונעים אשר באים להפר ברית שיש לאברהם עם הקדוש ברוך הוא, והמונעים האלו הם המלכיות, תמיד הם מתנגדים אל ישראל, שלא היה צריך אברהם לברית אלא מפני כחות האומות המתנגדים לאברהם תמיד. ולכך היה כורת וגוזר את הבהמות לשנים ועבר בין הגזרים שההעברה ביניהם הוא כריתת הגזרים והנצוח להם על ידי ההעברה בתוכם. ולפיכך חלק כל ג' מינים, אמנם הצפור לא בתר, ויש לזה טעם נפלא כי לקטנות הרביעית אין לה כריתה והעברה בתוך הגזרים, והחלק יורה על החשיבות כי בדבר גדול שייך חלוק כמו שהתבאר למעלה, ועם שהיא מלכות גדולה מאוד מאוד וטבעה יוצא בכל העולם אבל בעצמה קטנה. וזהו שדרשו תור הוא אלא שהוא גזלן, וזה סוד גדול בענין אדום שהוא אומה פחותה בעצמה אלא שהיא גוזלת, וכמו הגזלן אשר יש לו ממון רב בשקר, ואין זה בעצמו מצד שנתן השם יתברך אליו רק גוזל אחרים, כך חשיבות אדום אינו מפני עילוי מציאותם בעצמם עד שיהיו חשובים בעצמם, רק הם מציאות שקר ולוקח דבר שאינו ראוי, וכל הדברים האלו ידועים בענין אדום שהוא קטן ובזוי לפני הקדוש ברוך הוא ובעולם הזה הוא גדול: ובמדרש רבה בפרשת תולדות (פ' ס\"ה) ויקרא את עשו בנו הגדול אביו קראו גדול שנאמר ויקרא את עשו בנו הגדול אמו קראו גדול דכתיב את בגדי עשו בנה הגדול אמר להם הקדוש ברוך הוא אם בעיניכם הוא גדול בעיני הוא קטן שנאמר הנה קטן נתתיך בגוים בזוי. בארו בזה כי בעולם הזה נראה עשו גדול, אבל לפי האמת אמר הקדוש ברוך בעיני הוא קטן מאוד, ודבר זה ידוע למבינים, ומפני כך היה דומה לתור שהוא קטן. וידוע כי כאשר ראה דניאל ד' מלכיות אמר על חיה רביעית (דניאל ז') דהיא שניא לכך גם כן היה המין הד' משונה ממינים הראשונים שהיו בהמות, וזה תור וגוזל. והיה כל מין בהמות, ופרשו בזה שהמלכות כל אחת ואחת היו ג' רק הרביעית היא שניא מכל הראשונות. כי מלכות בבל היא שלשה נבוכדנצר אויל מרודך בלשצר, וכן מלכות מדי כורש דריוש אחשורוש, וכן מלכות יון שכבשו שלש רוחות נחשב ג' מלכיות, ומזרח לא היו יכולין לכבוש כי מזרח מעלתו יותר עליון מן שאר הרוחות כי הוא התחלה שמשם האורה בא לעולם, ולכך זה הרוח יש לו עילוי יותר ולא נתעלה מלכות יון על צד המזרח שהוא התחלה: ואל תתמה שקרא הכתוב כאן מלכות מדי עז ומלכות יון איל, ומצאנו הכתוב הפך זה שנקרא מלכות מדי איל שנאמר (דניאל ה') ראיתי האיל והנה מנגח וגו' ומלכות יון נקרא הצפיר השעיר. כי שניהם אמת, כי כאשר הוא מונה אותם דרך חשיבות קורא מלכות בבל שור כי השור הוא מין חשוב ולפיכך קראה עגלה משלשת, וקורא מלכות מדי עז שהוא פחות מזה, וקורא מלכות יון איל מפני שהאיל חשוב מן העז כדאיתא במסכת פסחים (דף נ\"ז ע\"ב), והיה מלכות יון יותר חשוב מן מלכות מדי, ולפיכך במקום הזה קורא אותם בשם הזה, והתבאר פירוש מי שאמר שהראה לו המלכיות: ולפי דבר אחר שרי אומות העולם הראה לו, פי' שהשם יתברך הראה לו שרי אומות העולם של מעלה יושבים כסאות בממשלתם זה כנגד זה, שכל ממשלתם אינו באחדות רק זה נגד זה, ולכך נתן איש בתרו נגד רעהו, והוא יתברך גבוה על הכל עבר בין הגזרים כי ממשלתו יתברך על הכל והוא שגוזר וכורת ברית עם אברהם, כי מה שגזר הברית הוא ממשלה כמלך שגזר דבר אחד כך היה הקדוש ברוך הוא גוזר לתת הארץ. ולמאן דאמר שרי ישראל הראה לו, ר\"ל שהראה לו סנהדרי ישראל יושבת וחותכת דיניהם של ישראל. ומפני זה עבר הקדוש ברוך הוא שם וגזר גם כן דיניהם של ישראל. ומפני שדין בית דין של מטה יש מחייבין ויש כנגדן מזכין. ולפיכך נתן איש בתרו נגד רעהו ודין השם יתברך מכריע בין הכל והבן זה. ומדמה את סנהדרין בית דין של מטה אל בהמות נגד דין העליון כי כן נדמה מדענו אל מדעו, ותדע כי לכל אחד מן הדעות יש טעם מופלג: ואת הצפור לא בתר הראה לו כל מי שאינו מעמיד פנים בגל אין הגל שוטפו. ויש לך לדעת כי מראה זה יש לו פנים הרבה, ודבר זה תמצא בתורה הרבה שיש דבר שיש לו פנים הרבה, וכן הענין במראה זה שלגודל ענינו יש לו פנים הרבה. כי מצד שהם ב\"ח, יש להם הוראה בפני עצמו שהיה מורה על ישראל, שיהיו נעשים כמו אלו דברים שהם בלא גידים ועצמות ויעמוד להם זכות אברהם. וזה בודאי כאשר אין לישראל זכות עצמם אז יעמוד להם זכות אברהם ראשית התחלתם, כי כל דבר שהתחלתו ועיקרו טוב הנה יש לו תקנה להחזיר אותו למעלתו הראשונה. ולפיכך מאחר שהתחלת ישראל טוב שהם בני אבות, יש להם תקון לכך זכות אברהם יעמוד להם שמחזיר אותם להיות כבתחלה, ואם לא היה התחלתם הטובה הזאת כאשר חטאו כבר היו נאבדים לגמרי ח\"ו, והבן דבר זה. ולכך אמרו וירד העיט הוא האויב לכלותם, אז וישב אותם אברם בתשובה כי יחזרו ישראל בתשובה בשביל אברהם שהוא ראשית התחלתם כמו שהתבאר: ועתה אל תתמה כאשר תמצא במדרש כי שלשה מיני בהמות הם רמז לאומות ותור וגוזל הם רמז לישראל שנקראים יונה, כי בודאי המראה הזה יש לו פנים לכל צד, ומתחלק לענינים הרבה. ומפני כי תור וגוזל שהוא קטן נגד מין בהמה מתפרש על ישראל מפני שהם קטנים דכתיב (דברים פרשה ז') לא מרבכם מכל העמים גו' כי אתם המעט, וזה שהם מקטינים עצמם כדאיתא בפרק כסוי הדם (חולין דף פ\"ט ע\"א) הנה הוראת התור והגוזל על ישראל במה שהם מקטינים עצמם. ותור וגוזל מורה על אדום גם כן שהם קטנים בעצמם ועושים עצמם גדולים. כלל הדבר המראה הזה הוא שהראה לו הדברים העתידים, כי אברהם הוא שהיה ראש ועיקר ישראל, וידוע כי אחר התחלת הדבר ימשך הכל שהענין נותן כך שימשך הכל אחר התחלה, ועכשיו הוא התחלת בחירת אברהם במה שכרת עמו ברית והראה לו כל דבר אשר ימשך. וכאשר אמרו האצטגנינים, כי כאשר יולד האדם באותה שעה יוכל לראות כל מקריו אשר הם עתידים לבא עליו כל ימיו, מצד אשר הכל נמשך אחר שעה ראשונה. ולפיכך גם כן כאשר הקדוש ברוך הוא כרת ברית עם אברהם, היא שעה ראשונה אשר לקח הקדוש ברוך הוא אותו אליו, והראה לו העתידות כלם אשר ראוי לבא על ישראל: ובמדרש רבות בפרשת לך לך (פ' מ\"ו) ר' יודן ור' יוחנן בן זכאי חד אמר העולם הזה הראה לו העולם הבא לא הראה לו ואוחרנא אומר אף העולם הבא הראה לו, ר' ברכיה אמר ר\"א ור' יוסי חד אמר עד היום הזה גלה לו וחד אמר עד היום ההוא גלה לו. ופירוש מחלוקתם, מפני שאברהם היה בעולם הזה לכך הראה לו כל הדברים הנמשכים אחר התחלה דהיינו העולם הזה, אבל העולם הבא מצד שהוא עולם אחר לא יאמר בזה שהראה מה שנמשך אחר ההתחלה. ואוחרנא אומר שאף עולם הבא הראה לו, כי הכל הוא בכח אברהם שהיה ראשית בנין של עולם כאשר אמרנו פעמים הרבה, ואם הוא ראשית בנין העולם הזה נמשך גם כן אחריו העולם הבא, שהעולם הבא בא אחר העולם הזה ונמשך אחריו ולפיכך ס\"ל שגם עולם הבא הראה לו. ומחלוקת ר\"א ור' יוסי שמי סובר עד היום הזה הראה לו, ר\"ל הדברים אשר יהיו בעולם הזה, אבל לא לימות המשיח, ימי המשיח נקראים יום ההוא בעבור העלם היום ההוא. ולאידך אף ימות המשיח שנקרא יום ההוא הראה לו עוד שם גם קריעת ים סוף הראה לו, שנאמר אשר עבר בין הגזרים ונאמר לגוזר ים סוף לגזרים. בארו בזה דבר מופלא מאוד, כי הראה לאברהם המעלה הגדולה אשר יש לישראל שהם מושלים על עולם הטבע, שהטבע נדחה מפניהם, והיה זה בקריעת ים סוף שהיתה הטבע נדחית מפניהם. והראה זה לאברהם במה שעבר בין הגזרים, כי הגזרים האלו מורים על הדברים החומרים, שכל בהמה היא חמרית וכאשר היה עובר בין הגזרים מורה שהברית שיש לאברהם עם הקדוש ברוך הוא הוא למעלה מן הטבע, והנהגתו שנוהג עם אברהם הוא על ידי בטול הטבע לכך היה עובר בין הגזרים, לומר כי הנהגת הקדוש ברוך הוא עם אברהם בביטול הטבע ולדחות אותה ולכן הראה לו בזה קריעת ים סוף. ותיישב הדברים האלו אחד לאחד ותמצא דברים נפלאים מאוד מאוד. והראה לו עוד הקדוש ברוך הוא ענין שיעבוד ד' מלכיות אשר ישעבדו בבניו, שזהו גם כן מן עיקר הדברים אשר ימשך אל זרעו. וזהו שדרשו שם אימה זה בבל שנאמר באדין נבוכדנצר התמלא חמה, חשיכה זו מדי שהחשיכו עיניהם של ישראל בצום ובתענית, גדולה זו יון שמעמדת ששים דוכסים ששים אפרכין ששים אסטרלסים, נופלת זו אדום שנאמר מקול נפלה רעשה הארץ. ויש מחליפין נופלת זו בבל שנאמר נפלה בבל, גדולה זו מדי שנאמר אחר הדברים האלה גדל המלך את המן, חשיכה זו יון שהחשיכה עיניהם של ישראל בגזירתן, אימה זו אדום דכתיב וארו חויא אמתני ותקיפה: דע אין מחלוקת אלו הפירושים בביאור המלות בלבד, אבל כל אחד מפרש ענין ד' מלכיות אלו. כי ידוע שבבל הוא רישא דדהבא שהיו מקבלין כחם ממדה שממנה הזהב, ומפני המדה הזאת נמצא בהם האימה, כי האימה נמשכת מן מדה זאת שהוא מדת הדין, ולפיכך הביא ראיה באדין נבוכדנצר התמלא חימה. חשיכה זו מדי, ידוע כי מדי מוכן להעדיר את ישראל וזה נקרא חשיכה, כי המציאות נקרא אור והעדר נקרא חושך, וזהו הגורם שנתן המלך יד להמן זרע עשו שכחו נמשך מן סמאל לאבד ישראל ח\"ו לכך חשיכה זו מדי. והבן מה שאמר שהחשיכה עיניהם של ישראל בצום ובתענית, שזהו בעצמו בטול והעדר האדם ע\"י צום ותענית. גדולה זו יון מפני שמעמדת ששים דוכסים כו'. והדברים האלו ידועים מענין יון שיש לה רבוי של ששים, מפני שהיא מלכות שלישית והשלישי הוא התחלת הרבוי. אמרו חכמים ימים שנים רבים שלשה, רצו בזה כי התחלת הרבוי הוא שלשה לכך מלכות יון שהיא מלכות שלישית היתה גדולה ומרובה בשררות ובממשלות, עד שהיתה מעמדת ששים דוכסים, כי ששים נאמר על הרבוי בכל מקום. נופלת זו אדום. יש לך לדעת למה נקרא אדום נופלת, כי תפול נפילה ממקום גבוה מאוד, וזה יקרא נפילה כדכתיב (עובדיה א') כי תגביה כנשר קנך משם אורידך: וכאשר תבין ד' מלכיות אלו תדע, כי שלש מלכיות כל אחת ואחת היתה מושלת על ישראל בענין מיוחד. ויש באדם ג' דברים גוף ונפש ושכל, שלש מעלות זו על גבי זו. ומלכות בבל לא היה רוצה רק לשעבד אותם בגוף כדכתיב (ירמיה כ\"ח) הביאו עצמכם בעול מלך בבל וחיו. ולא תמצא שהיה נבוכדנצר חפץ רק עול על ישראל, ולפיכך לא היה שיעבוד של מלכות רק בגוף. ומלכות מדי היה רוצה לקחת נפשם, ומלכות יון לבטל אותם מתורתן שהיא שלימות השכל. ומלכות רביעית בשלשה משעבדין בהם בעול והרוגי מלכות וגזרו שלא יתעסקו בתורה. וזה מפני כי מלכות רביעית שקולה כנגד ג' מלכיות. ודבר זה תמצא מבואר בויקרא רבה פרשת שמיני (פ' י\"ג) ולפיכך מלכות בבל נקרא אימה, שזהו מורה חשיבות שאימתו מוטלת על אחר ואין החשוב מבקש רק שררה. ומדי נקרא חשיכה שהחושך הוא העדר לכך היה מבקש ח\"ו לכלות ישראל. ויון נקראת גדולה שמפני גדולתם היו רוצים לבטל התורה שהיו מקנאין בהם, ואין גדול מתקנא רק בגדול שכמותו. ויש מחליפין נופלת זו בבל דכתיב נפלה בבל, פירוש לפי שנקרא רישא דדהבא והראש הוא בעליון ובגובה לפיכך נקרא השפלתה נפילה. גדולה זו מדי שנאמר אחר הדברים האלה גדל המלך, ענין זה של גדל רמז על מלכות מדי שהיה מגדל את המן, כאשר אמרו במדרש שהיה אחשורוש מגדל את המן יותר ממנו. וכך אמרו במדרש (ילקו\"ש תתרנ\"ג) וישם את כסאו שהיה עושה לו בימה למעלה מבימתו והוא לגודל כח עמלק שנאמר (שמות י\"ב) כי יד על כס יה וגו', ומזה תבין גודל כח המן שהוא זרע עמלק שעליו בפרט אמר (שם) מלחמה לה' בעמלק. ולפיכך היה המן שהוא מזרע עמלק ראוי לגדולה יותר מן אחשורוש, וראוי שיקרא מלכות זה גדול שהרי יש בו גדולה יתירה על ידי המן: ואין להקשות כי דבר זה לא היה לאחשורוש אדרבא היה שפלות דבר זה אליו. זה אין קשיא דסוף סוף היה מלכות זה מיוחד בדבר זה להגדיל אדם שיהיה גדול מאוד, ומה שהיה מגדיל אותו יותר מן המלכות, שהיה חושב שעל ידי גדולה זאת וחשיבות זה יקבל כל מלכותו גדולה כאשר יש במלכותו מי שהוא גדול כמו זה שהיה גדול מן המלכות. ולפיכך היה גדולה זאת ענין מענין ע\"ז להמן, וזה שאמרו במדרש (אסתר רבה פ\"ו) שלא היה רוצה מרדכי להשתחות לו שעשה עצמו עבודה זרה, וכל זה שהיה גדול מן המלכות ואין גדול מן המלכות רק האלהות, ולכך אמרו גדולה זו מדי שהגדילה המן יותר מן המלכות. חשיכה זו יון שהיתה מחשכת עיניהם של ישראל בגזירות, מפני שמלכות יון היו רוצים לבטל מישראל התורה, ולפיכך מלכות זה שגזרה על מצות התורה שהיא האור דכתיב (משלי ו') כי נר מצוה ותורה אור נקראת חשיכה. אימה זו אדום מפני שהיא תקיפא ואמתני כאשר אמר הכתוב. וכאשר תדקדק בפי' הזה תמצא כי לשני הפירושים כל מלה ומלה מורה על עצם המלכיות: עוד שם ויהי השמש לבא ועלטה היה והנה תנור עשן ולפיד אש אשר עבר בין הגזרים ר\"ש בן אבא משום רבי יוחנן ד' דברים הראה לו הקדוש ברוך הוא גהינם ומלכות מתן תורה ובית המקדש אמר לו כל זמן שבניך עוסקים בשתים ינצלו משתים פרשו משתים נופלים בשתים אמר לו במה אתה רוצה שירדו בניך או בגיהנום או במלכות ר' חנינא בר פפא אמר אברהם ברר לו המלכיות ר' יודן ור' אבא ור' חמא בר חנינא אמר אברהם ברר את הגיהנם והקדוש ברוך הוא ברר לו המלכיות, כי המלכיות נקרא תנור עשן שאין העשן שורף בחזקה, ונקרא הגיהנם לפיד אש כלפי המלכיות שהם עשן. והראה לו התורה ובית המקדש, התורה הראה לו שהרי קרבנות אלו עגלה משולשת רומזים על הקרבנות שהם בתורה, וכן הראה לו בית המקדש שהרי הקרבנות מקריבים בבית המקדש וכל זמן שעוסקים בניך בשתים שהם התורה ובית המקדש נצולו משתים. כי כמו שבאדם הלב והמוח עיקר האדם, הלב שממנו החיות והמוח ששם השכל, כך בכלל העולם בית המקדש והתורה עיקר מציאות העולם. וכמו שהלב הוא באמצע האדם וממנו מקבלים חיות ושפע כל האברים, כך בית המקדש באמצע העולם ממנו מקבלים כל הארצות חיות ושפע. וכמו שהמוח שם שכל האדם, כך התורה שכל העולם, נמצא התורה ובית המקדש הם עיקר הנמצאים, ואלו שני דברים הם צמודים ביחד תמיד, כי בית המקדש הוא בארץ והוא שלימות העולם הגשמי, והתורה היא שכלית, ולפיכך על אלו שני דברים אומרים תמיד יהי רצון שיבנה בית המקדש במהרה בימינו ותן חלקנו בתורתך, לומר שיתן חלקנו בדבר שנחשב מציאות דוקא ולא בדברים בטלים: ומפני שאלו שניהם עיקר המציאות, כאשר הם עוסקים בשתים ינצלו מן האומות והגיהנם, כי האומות וגיהנם אינם מציאות, כי על האומות נאמר (ישעי' מ') כל גוים תהו ומכל שכן הגיהנם שהוא ציה וחושך ואין מציאות שם לכך פרשו משתים נופלים בשתים, וזה הפירוש ברור מאוד. ואמר לו הקדוש ברוך הוא בחר לך באחד מהם, כלומר בניך יקבלו הפסד או במלכיות או בגיהנם, כי כשם שהגיהנם הוא הפסד כך המלכיות הם אבוד שבהם ישראל אבודים, ואמר רב חנינא בר פפא שאברהם ברר לו המלכיות שודאי יותר היה ראוי לברר לו המלכיות כי המלכיות דינם יש לו קץ, אבל הגיהנם לעולם. ור' יודן ור' אבא סבירי להו שאברהם ברר לו הגיהנם, שכן דרך הצדיקים לשנאת עושה רשעה ואומר על הרשעים שירדו לגיהנם, ולפיכך ברר אברהם את הגיהנם, אבל הקדוש ברוך הוא מלא רחמים מביא יסורים על האדם לכפר ולנקותו ולהצילו מן הגיהנם ולכן ברר לו המלכיות, כי כאשר יבאו במלכיות יחשבו כאלו הם אבודים ונפסדים, ואז יוציא אותם הקב\"ה מהם ויחדש אותם ברי' חדשה וכאשר תבין דברים אלו תדע אמיתתם כי הם דברים ברורים מאוד:\n";

    @Before
    public void before() throws Exception {
        index = JbsTanachIndex.instance();
    }

    @Test
    public void getDocumentByURI() {
        List<org.apache.lucene.document.Document> result = index.searchExactInUri("jbr:text-tanach-28-1-1");
        index.printDocs(result);
    }

    @Test
    public void getDocumentByText() {
        List<org.apache.lucene.document.Document> result = index.searchFuzzyInText("אל הארץ אשר אראך", 2);
        System.out.println(result.size() + " results:");
        index.printDocs(result);
    }

    @Test
    public void printSpannedDocument() {
        doc = new NgramDocument(textMitzva_1_1, JbsMekorot.MINIMAL_PASUK_LENGTH, JbsMekorot.MAXIMAL_PASUK_LENGTH);
        JbsMekorot.findPsukim(doc);

        System.out.println(doc.toString());
    }
}
