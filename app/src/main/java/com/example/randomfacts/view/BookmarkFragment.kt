package com.example.randomfacts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomfacts.R
import com.example.randomfacts.adapter.BookmarkAdapter
import com.example.randomfacts.adapter.RvAdapter
import com.example.randomfacts.databinding.FragmentBookmarkBinding
import com.example.randomfacts.databinding.FragmentHomeBinding
import com.example.randomfacts.model.DataModel
import com.example.randomfacts.util.SharedPre
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.log


class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private lateinit var rv_adapter: BookmarkAdapter
    private val json = "[{\n" +
            "\t\t\"description\": \"During the 1870s, two well-known inventors both independently designed devices that could transmit sound along with electrical cables. Those inventors were Alexander Graham Bell and Elisha Gray. Both devices were registered at the patent office within hours of each other. There followed a bitter legal battle over the invention of the telephone, which Bell subsequently won.\\r\\n\\r\\nThe telegraph and telephone are very similar in concept, and it was through Bell's attempts to improve the telegraph that he found success with the telephone.\\r\\n\\r\\nThe telegraph had been a highly successful communication system for about 30 years before Bell began experimenting. The main problem with the telegraph was that it used Morse code, and was limited to sending and receiving one message at a time. Bell had a good understanding of the nature of sound and music. This enabled him to perceive the possibility of transmitting more than one message along the same wire at one time. Bell's idea was not new, others before him had envisaged multiple telegraphs. Bell offered his own solution, the \\\"Harmonic Telegraph\\\". This was based on the principle that musical notes could be sent simultaneously down the same wire if those notes differed in pitch.\\r\\n\\r\\nBy the latter part of 1874 Bell's experiment had progressed enough for him to inform close family members about the possibility of multiple telegraphs. Bell's future father in law, attorney Gardiner Green Hubbard saw the opportunity to break the monopoly exerted by the Western Union Telegraph Company. He gave Bell the financial backing required for him to carry on his work developing the multiple telegraphs. However, Bell failed to mention that he and his accomplice, another brilliant young electrician Thomas Watson, were developing an idea that occurred to him during the summer. This idea was to create a device that could transmit the human voice electrically.\\r\\n\\r\\nBell and Watson continued to work on the harmonic telegraph at the insistence of Hubbard and a few other financial backers. During March 1875 Bell met with a man called Joseph Henry without the knowledge of Hubbard. Joseph Henry was the respected director of the Smithsonian Institution. He listened closely to Bell's ideas and offered words of encouragement. Both Bell and Watson were spurred on by Henry's opinions and continued their work with even greater enthusiasm and determination. By June 1875 they realized their goal of creating a device that could transmit speech electrically would soon be realized. Their experiments had proven different tones would vary the strength of an electric current in a wire.\",\n" +
            "\t\t\"fact\": \"Music was sent down a telephone line for the first time in 1876, the year the phone was invented.\",\n" +
            "\t\t\"id\": \"1070\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-08-08 10:39:12\",\n" +
            "\t\t\"source\": \"http://www.nationalitpa.com/history-of-telephone\",\n" +
            "\t\t\"title\": \"Music in Telephone Line\",\n" +
            "\t\t\"topic\": \"Music\",\n" +
            "\t\t\"topic_id\": \"12\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"Like many famous songs, Nirvana's seminal hit Smells Like Teen Spirit has an unusual history. For starters, the author of the 1991 song (Nirvana's lead singer Kurt Cobain) did not know what the term \\\"teen spirit\\\" meant when he used it as the title; he thought it was an arcane anti-establishment motto, when in fact it was the name of a mildly popular deodorant aimed at young females.\\r\\n\\r\\nSmells Like Teen Spirit is generally conceded to be one of the most epochal songs in the history of rock'n roll. This is because it constitutes the fault line separating the \\\"alternative\\\", grunge era of the early Nineties from the \\\"hair-band\\\" era of the Eighties. As has been noted before, Nirvana was both the kiss of death and the death of Kiss. Grunge had been around for quite some time before Kurt Cobain showed up, but Smells Like Teen Spirit was more catchy and sophisticated than the average grunge song. It was both primitive and complex, both cerebral and coarse. From the moment MTV began playing the grainy, bizarre Teen Spirit video on a late-night program devoted to music it didn't really expect its audience to enjoy, the inane, interchangeably pointless hair bands that had dominated the previous decade understood that their time had passed. Except for Bon Jovi.\\r\\n\\r\\nNo one knows where the riff that defines Smells Like Teen Spirit comes from, other than from Kurt Cobain's head. But the phrase \\\"smells like teen spirit\\\" had been scrawled on the wall of his apartment by Katherine Hanna, the lead singer of the band Bikini Kill. Hanna wrote this as a joke because her bandmate Tobi Vail, who happened to be Cobain's girlfriend at the time, was a fan of the Teen Spirit deodorant. Teen Spirit was manufactured by the Mennen Corporation, which had developed a line of very popular, somewhat \\\"alternative\\\" deodorants for men in the 1960s.\\r\\n\\r\\nBecause Bikini Kill was recorded on the Kill Rock Stars label and was generally contemptuous of mainstream American society, Cobain mistook the phrase for a seditious catchphrase, like \\\"\\u00c0 Nous La Liberte!\\\" or \\\"El Pueblo Unido Jamas Sera Vencido.\\\" Cobain, it will be recalled, grew up in suburban Washington State, which had no history of sedition, is best known for logging, fishing, anomie, and precipitation. When Cobain found out what Teen Spirit was, he was highly upset and very possibly humiliated, but as he didn't live very long, naming a song after a well-liked deodorant probably didn't hurt his career.\",\n" +
            "\t\t\"fact\": \"Kurt Cobain accidentally named Nirvana's \\\"Smells Like Teen Spirit\\\" after a deodorant for teenage girls.\",\n" +
            "\t\t\"id\": \"1089\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-06-05 10:33:02\",\n" +
            "\t\t\"source\": \"https://www.theguardian.com/music/2007/jul/19/popandrock.nirvana\",\n" +
            "\t\t\"title\": \"Smells Like Teen Spirit\",\n" +
            "\t\t\"topic\": \"Music\",\n" +
            "\t\t\"topic_id\": \"12\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"Fifty years to start growing acorns seems like an immense amount of time. Pollination between the male and female flowers of the tree plays a role in how long it takes for the acorn fruit to begin blooming.\\r\\n\\r\\nThe majority of oak trees do indeed bloom acorns well before the age of 50. \\\"Most species of oaks begin producing acorns at about 20 years old. Peak production occurs from about 50 to 80 years,\\\" reports the University of Tennessee Extension service. Oak trees younger than 50 are likely producing acorns, but perhaps not in especially noticeable numbers.\\r\\n\\r\\nAcorn production from an oak tree older than 80 tends to be minimal, and certain varieties of oak emit more acorns than others. Healthy oaks with majestic, prominent crowns that tower over other tree canopies will bloom more of the seed than weakened, unhealthy oaks. Sometimes extreme production one year will be followed by a season of nominal blooming as the oak regains its energy.\",\n" +
            "\t\t\"fact\": \"Oak trees don't reach peak acorn production until they are 50 years old.\",\n" +
            "\t\t\"id\": \"1676\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-08-28 10:47:00\",\n" +
            "\t\t\"source\": \"https://www.gardenguides.com/12463273-why-oak-trees-dont-produce-acorns-until-they-are-50-years-old.html\",\n" +
            "\t\t\"title\": \"Oak Trees\",\n" +
            "\t\t\"topic\": \"Time\",\n" +
            "\t\t\"topic_id\": \"19\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"Chess in the 6th century was known as chaturanga, which translates as 'four divisions of army'.\\r\\n\\r\\nThe earliest predecessor of the game originated in India, before the 6th century AD. From India, the game spread to Persia. When the Arabs conquered Persia, chess was taken up by the Muslim world and subsequently spread to Southern Europe.\\r\\n\\r\\nIn Europe, chess evolved into roughly its current form in the 15th century. Romantic chess was prefering the late 15th century to the 1880s.The Romantic era of play was followed by the Scientific, Hypermodern, and New Dynamism eras.\",\n" +
            "\t\t\"fact\": \"Chess originated in northern India in the 6th century AD.\",\n" +
            "\t\t\"id\": \"2835\",\n" +
            "\t\t\"isPremium\": 1,\n" +
            "\t\t\"last_update\": \"2021-08-08 10:38:11\",\n" +
            "\t\t\"source\": \"https://en.wikipedia.org/wiki/History_of_chess\",\n" +
            "\t\t\"title\": \"The Origin of Chess\",\n" +
            "\t\t\"topic\": \"India\",\n" +
            "\t\t\"topic_id\": \"46\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"There are sources claims that the origin of sleeves buttons was in the 18th century. Commanders ordered these brass buttons to be sewn on the front of the sleeve cuffs of expensive. This was done to make sure that the soldiers and the sailors do not wipe their mouths and noses on their sleeves.\\r\\n\\r\\nThere is a belief that this action was taken in the concern of boys, young recruits, and men. As per sources, the boys cried a lot and used to wipe their tears and nose with sleeves. So the buttons were sewn to make it difficult for them. Every time a soldier tries to wipe his face or nose, his face would scratch by the button.\",\n" +
            "\t\t\"fact\": \"The buttons were first invented for sleeves after soldiers kept wiping their noses on their button-free clothes in the 18th century.\",\n" +
            "\t\t\"id\": \"2842\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-08-08 10:40:38\",\n" +
            "\t\t\"source\": \"https://www.procaffenation.com/story-buttons-napoleon-uniform/\",\n" +
            "\t\t\"title\": \"Origins of Sleeves Buttons\",\n" +
            "\t\t\"topic\": \"Fashion\",\n" +
            "\t\t\"topic_id\": \"47\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"English writer Guy Beringer is credited with first proposing the idea for the meal in his 1895 essay \\\"Brunch: A Plea.\\\" In the piece, which was published in Hunter's Weekly, he argues that brunch would serve as the perfect remedy for Sunday morning hangovers and it could be the perfect social gathering at which to share stories of Saturday night's debauchery.\\r\\n\\r\\nThe term brunch has a second origin in the U.S.: many credited it to reporter Frank Ward O'Malley who wrote for the NY newspaper The Sun from 1906 until 1919. It was reportedly based on the typical mid-day eating habits of a newspaper reporter. It seems O'Malley, whom H.L. Mencken called \\\"one of the best reporters America has ever known,\\\" had a reputation for phrasemaking, with such gems as, \\\"Life is just one damned thing after another.\\\"\\r\\n\\r\\nBrunch finally took off in America in the 1930s in Chicago; according to Evan Jones, author of \\\"American Food: The Gastronomic Story,\\\" it became popular because movie stars, celebrities, and the wealthy would stop for late morning meals while taking transcontinental train rides. After a decline in American churchgoers post-WWII, Jones explained to the NY Times why brunch gripped the nation like a fever: \\\"We like to sleep on Sundays, read the newspapers and loll in bed. After the World War II generation went away from church altogether, Sunday became a day to enjoy doing nothing and brunch just grew like topsy\\\".\",\n" +
            "\t\t\"fact\": \"\\\"Brunch\\\" was created to cure hangovers, in 1895 by an English writer named Frank Beringer.\",\n" +
            "\t\t\"id\": \"2979\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-08-08 10:42:18\",\n" +
            "\t\t\"source\": \"https://gothamist.com/food/yep-brunch-really-was-created-to-cure-hangovers\",\n" +
            "\t\t\"title\": \"The Origin of Brunch\",\n" +
            "\t\t\"topic\": \"Food\",\n" +
            "\t\t\"topic_id\": \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"The Celts, who lived 2,000 years ago, mostly in the area that is now Ireland, the United Kingdom and northern France, celebrated their new year on November 1.\\r\\n\\r\\nThis day marked the end of summer and the harvest and the beginning of the dark, cold winter, a time of year that was often associated with human death. Celts believed that on the night before the new year, the boundary between the worlds of the living and the dead became blurred. On the night of October 31, they celebrated Samhain when it was believed that the ghosts of the dead returned to earth.\\r\\n\\r\\nTo commemorate the event, Druids built huge sacred bonfires, where the people gathered to burn crops and animals as sacrifices to the Celtic deities. During the celebration, the Celts wore costumes, typically consisting of animal heads and skins, and attempted to tell each other's fortunes.\\r\\n\\r\\nWhen the celebration was over, they re-lit their hearth fires, which they had extinguished earlier that evening, from the sacred bonfire to help protect them during the coming winter.\",\n" +
            "\t\t\"fact\": \"Halloween's origins date back to the ancient Celtic festival of Samhain.\",\n" +
            "\t\t\"id\": \"3026\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-08-08 10:42:55\",\n" +
            "\t\t\"source\": \"https://www.history.com/topics/halloween/history-of-halloween\",\n" +
            "\t\t\"title\": \"The Origin of Halloween\",\n" +
            "\t\t\"topic\": \"History\",\n" +
            "\t\t\"topic_id\": \"10\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"In 1905, a San Francisco Bay Area kid by the name of Frank Epperson accidentally invented the summertime treat. He had mixed some sugary soda powder with water and left it out overnight. It was a cold night, and the mixture froze. In the morning, Epperson devoured the icy concoction, licking it off the wooden stirrer. He declared it an Epsicle, a portmanteau of icicle and his name, and started selling the treat around his neighborhood.\\r\\n\\r\\nIn 1923, Epperson decided to expand sales beyond his neighborhood. He started selling the treat at Neptune Beach, a nearby amusement park. Dubbed a \\\"West Coast Coney Island,\\\" the park featured roller coasters, baseball and an Olympic-sized swimming pool. Neptune flourished in the pre-Depression days, and consumers eagerly consumed Epsicles and snow cones (which also made their debut at Neptune).\\r\\n\\r\\nBuoyed by this success, Epperson applied for a patent for his \\\"frozen confection of attractive appearance, which can be conveniently consumed without contamination by contact with the hand and without the need for a plate, spoon, fork or other implements\\\" in 1924. The patent illustrates the requirements for a perfect ice pop, including recommendations on the best wood for the stick: wood-bass, birch and poplar. Eventually, Epperson's children urged him to change the ice pop's name to what they called it: a Pop's 'Sicle or Popsicle.\",\n" +
            "\t\t\"fact\": \"Ice pops were accidentally invented by an 11-year-old boy, in 1905. He left soda powder and water outside overnight with its wooden stirrer still in the cup.\",\n" +
            "\t\t\"id\": \"3172\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-08-08 10:44:06\",\n" +
            "\t\t\"source\": \"https://www.npr.org/sections/thesalt/2015/07/22/425294957/how-an-11-year-old-boy-invented-the-popsicle\",\n" +
            "\t\t\"title\": \"The Origin of Ice Pop\",\n" +
            "\t\t\"topic\": \"Food\",\n" +
            "\t\t\"topic_id\": \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"Gin likely traces its origins to liquors produced back in the Middle Ages, with references to a spirit flavoured with \\u201cgenever\\u201d referenced in a 13th Century Flemish manuscript. By the 1600s, the Dutch were producing gin in earnest, with hundreds of distilleries in the city of Amsterdam alone.\\r\\n\\r\\nGin, like so many things, was originally produced as a medicine. It was distributed by \\u201cchemists\\u201d for the treatment of ailments such as gout and dyspepsia. Consumed in large enough quantities, it likely did help ameliorate perception of the symptoms associated these issues and many others, such as \\u201cCoward\\u2019s Fist,\\u201d though only for a few hours at a time. Gin gained in popularity doing the Thirty Years\\u2019 War when British soldiers fighting on Dutch land were bolstered with \\u201cDutch Courage\\u201d by drinking gin.\\r\\n\\r\\nIt didn\\u2019t take long for this lovely liquor to hop across the English Channel in a big way. In the latter half of the 17th Century and in the early years of the 18th Century, gin rapidly gained popularity in England, cementing the association it still enjoys with that nation. In fact, by the year 1720, some experts estimate that as many as a quarter of the households in London frequently produced their own gin. The period in the storied city\\u2019s history became known as \\u201cThe Gin Craze,\\u201d an era that was so awesome Parliament had to pass no fewer than five major legislative acts over the course of 22 years in a vain attempt to rein in the population\\u2019s consumption of gin. \\r\\n\\r\\nGin remained popular with the Brits, notable for its use by soldiers and colonials living in lands prone to malaria infections: gin was excellent at masking the unpleasant, bitter flavor of the anti-malarial alkaloid quinine, a necessity for the susceptible foreigners. This medical elixir developed into the Gin & Tonic we know and love to this day.\",\n" +
            "\t\t\"fact\": \"Gin was invented by the Dutch in the 17th century.\",\n" +
            "\t\t\"id\": \"3254\",\n" +
            "\t\t\"isPremium\": 0,\n" +
            "\t\t\"last_update\": \"2021-08-08 10:44:56\",\n" +
            "\t\t\"source\": \"https://www.themanual.com/food-and-drink/a-brief-history-of-gin/\",\n" +
            "\t\t\"title\": \"The Origin of Gin\",\n" +
            "\t\t\"topic\": \"Food\",\n" +
            "\t\t\"topic_id\": \"8\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"description\": \"In the Popol Vuh, Camazotz is the bat-like monsters encountered by the Maya Hero Twins Hunahpu and Xbalanque during their trials in the underworld of Xibalba. The twins had to spend the night in the House of Bats, where they squeezed themselves into their blowguns to defend themselves from the circling bats. Hunahpu stuck his head out of his blowgun to see if the sun had risen and Camazotz immediately snatched off his head and carried it to the ballcourt to be hung up as the ball to be used by the gods in their next ballgame.\\r\\n\\r\\nIn Part III, chapter 5 of the Popol Vuh, a messenger from Xibalba, in the form of a man with the wings of a bat, brokers a deal between Lord Tohil and mankind, wherein mankind promises their armpits and their waists (the opening of their breasts in human sacrifice) in exchange for fire.\",\n" +
            "\t\t\"fact\": \"In Maya mythology, Camazotz is a bat god. Camazotz means \\\"death bat\\\" in the Mayan language. In Mesoamerica, the bat is associated with night, death, and sacrifice.\",\n" +
            "\t\t\"id\": \"3646\",\n" +
            "\t\t\"isPremium\": 1,\n" +
            "\t\t\"last_update\": \"2021-07-24 08:18:19\",\n" +
            "\t\t\"source\": \"https://en.wikipedia.org/wiki/Camazotz#:~:text=In%20Maya%20mythology%2C%20Camazotz%20(%2F,night%2C%20death%2C%20and%20sacrifice.\",\n" +
            "\t\t\"title\": \"Camazotz\",\n" +
            "\t\t\"topic\": \"Interesting\",\n" +
            "\t\t\"topic_id\": \"41\"\n" +
            "\t}\n" +
            "]\n"

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val view = binding.root
        setRv();

        return view
    }

    private fun setRv() {
        var list: ArrayList<DataModel> = parseData();
        binding.rvBookmark.apply {
            this.layoutManager = LinearLayoutManager(context);
            rv_adapter = BookmarkAdapter(context, list)
            this.adapter = rv_adapter
        }
    }

    private fun parseData(): ArrayList<DataModel> {
        val shared = SharedPre()

        var list: ArrayList<DataModel> = ArrayList();
        try {
            val obj = JSONArray(json)

            var listt: ArrayList<String> = shared.getList(requireContext())
            binding.txtShowNull.visibility = View.GONE
            if (listt.size == 0) {
                binding.txtShowNull.visibility = View.VISIBLE
            }
            for (a in 0 until listt.size) {
                for (i in 0 until obj.length()) {
                    if (listt.get(a).equals(obj.getJSONObject(i).getString("id"))) {
                        val m = DataModel();
                        m.description = obj.getJSONObject(i).getString("description")
                        m.fact = obj.getJSONObject(i).getString("fact")
                        m.id = obj.getJSONObject(i).getString("id")
                        m.isPremium = obj.getJSONObject(i).getInt("isPremium")
                        m.last_update = obj.getJSONObject(i).getString("last_update")
                        m.source = obj.getJSONObject(i).getString("source")
                        m.title = obj.getJSONObject(i).getString("title")
                        m.topic = obj.getJSONObject(i).getString("topic")
                        m.topic_id = obj.getJSONObject(i).getString("topic_id")

                        list.add(m);
                        //   Log.i("TAG", "parseData: " + obj.getJSONObject(i).getString("id"))
                    }

                }
            }


        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    companion object {
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//
//            }
//    }
}