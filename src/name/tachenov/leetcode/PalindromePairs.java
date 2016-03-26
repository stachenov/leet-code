/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author alqualos
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> buckets = new HashMap<>();
        Map<Integer, List<Integer>> revBuckets = new HashMap<>();
        String[] revWords = new String[words.length];
        for (int i = 0; i < words.length; ++i) {
            String s = words[i];
            String r = new StringBuilder(s).reverse().toString();
            revWords[i] = r;
            int h = 0, hrev = 0;
            for (int j = 0; j < s.length(); ++j) {
                h = h * 17 + s.charAt(j);
                hrev = hrev * 17 + r.charAt(j);
            }
            buckets.computeIfAbsent(h, key -> new ArrayList<>()).add(i);
            revBuckets.computeIfAbsent(hrev, key -> new ArrayList<>()).add(i);
        }
        for (int i = 0; i < words.length; ++i) {
            String s = words[i];
            int[] m = manacher(s);
            for (int j : findPairs(s, m, 0, buckets, revWords)) {
                if (i != j) {
                    result.add(Arrays.asList(j, i));
                }
            }
            s = revWords[i];
            for (int j = 0, k = m.length - 1; j < k; ++j, --k) {
                int tmp = m[j];
                m[j] = m[k];
                m[k] = tmp;
            }
            for (int j : findPairs(s, m, 1, revBuckets, words)) {
                if (i != j) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }
    
    private static List<Integer> findPairs(String s, int[] manacher, int end,
            Map<Integer, List<Integer>> buckets, String[] words) {
        List<Integer> pairs = new ArrayList<>();
        for (int i = s.length(), h = 0;; ) {
            assert i + manacher[i] <= 2 * i;
            if (i + manacher[i] == 2 * i) {
                List<Integer> bucket = buckets.get(h);
                if (bucket != null) {
                    for (int j : bucket) {
                        if (s.length() - i == words[j].length() && s.regionMatches(i, words[j], 0, words[j].length())) {
                            pairs.add(j);
                        }
                    }
                }
            }
            if (--i < end) {
                break;
            }
            h = h * 17 + s.charAt(i);
        }
        return pairs;
    }
    
    private static int[] manacher(String s) {
        final int n = s.length() * 2 + 1;
        int[] p = new int[n];
        for (int i = 0, c = 0; i < n; ++i) {
            int r = c + p[c], il, ir;
            if (i > r) {
                il = i - 1;
                ir = i + 1;
            } else {
                int i2 = c - (i - c);
                if (i + p[i2] >= r) {
                    ir = r + 1;
                    il = i - (ir - i);
                    p[i] = r - i;
                } else {
                    p[i] = p[i2];
                    il = ir = -1; // skip the check
                }
            }
            while (il >= 0 && ir < n && ((il & 1) == 0 || s.charAt(il / 2) == s.charAt(ir / 2))) {
                ++p[i];
                --il;
                ++ir;
            }
            if (i + p[i] > r) {
                c = i;
            }
        }
        return p;
    }
    
    public static void main(String[] args) {
        //System.out.println(new PalindromePairs().palindromePairs(new String[] {"aihhieeegdebhb","acajjajbbibaab","ddjihhebahcead","cehdaiaeaggai","di","bcacdfjadgfjgiicghc","ddhigcdidggfcejib","djbgdcadgiaaieh","fggfdajadcfhajhag","dfahbdjcbgdf","dfbhhdfjdcfefij","fjgbegdifedgf","ffeice","gdgjcaichica","gigjifehagcacggg","ehjgha","ddj","igffdiddgecidcjfj","cca","heeig","cdghidigcgdaeda","jbbachabahheidacd","feahagifaacejg","abdbgidheigfcabffe","ehcbieidgfagijafd","gcji","f","jddgd","ibachgei","eeabadcbiebdchiaha","baejcdeef","cagbjjhaecgi","ijfcfecdccchee","jhiidcdba","dgdibjihcbbhg","fcibbcbbjhhbiiiaag","fggefjgbjdajcbcgjbj","gg","hhadjfeaa","aghbgcjfcdcihfc","bfffcggfeddeajbhafge","dggfafhafc","dcdbhcifbbbgbcaeh","dacacdhbjd","ghhifd","aabifcjhadibhfdjbga","baejceehiagaabchabff","bjagbabf","hgijgdifccjdehjaccbf","gbfejbfddgcja","iiedgaigdg","eeieg","cei","bchicbfcihbcjejjfg","jhjdfi","ebdgaghfbjhfceh","bdjdgegfdddadiieaici","abeecj","g","jcadaigbcabcggeddjcd","h","ajjg","iebcbgfbjhbfbai","ghj","gidafdigfgfcjfg","hgjacihicbcfifghgcbc","gaaibecj","ifjciedfgdegfi","bfcbdddjfjjhfahbjfaa","fafgijfgahf","gcjacdbgdbecghif","gjdgah","fdbbcbidbhbgf","ghihccifb","ghjeecjgfefd","b","egigbdaicefieddicee","gjg","baae","jeiehgccjefedjib","jjcjgeaajcj","ihjbhbjgejgcheb","accce","jhc","gjj","icaadccfihj","bafefdchbhibibdfggg","egehfbegjedcicdfjfge","fbcdighdcieideh","bjfibbjigc","daidhjjb","aeeec","ed","dgdbjjibccjdafh","bbde","biejhahgd","bjhhdiidcbe","dibceifgj","c","hedi","fjjfihdejifjfba","hd","bffbd","gdjaaaaihgbiahicde","gdehfchcd","icgidjcj","gd","jddfjhcgiefjee","daajiighijhdfbi","bggj","ecgfbahaacfiajc","ggdffajibaidjib","jf","fhicadfghdcchiiifj","aji","gfjacgcfbicgdjad","cfhfcggadigidigihdid","bfcdidiifgfbc","ahiidhjhbfdiahic","edecdcd","cigigjfafdbhbdhhhjf","fbjefgghghcheja","dhcheddfjffeejb","ffcd","ificcdfdb","ghjgafhiffdcgghjja","jgfadaecjgh","fifahiijidjgcdiedee","dccejgdfaggdadfccd","aeghbjdjgifah","gejaehchdfhfdeegie","bafa","aebgccebdfcgc","abdgbcg","dcedgfjbbgc","jbfhaddbbdaiiegjic","jegdijdaebj","ejgajggidiahadgddeab","ece","i","hegjjfbgegijdjgb","aiiee","eefad","cdbeifbfa","afej","hagaadb","egfdib","hfaiididcidfjccf","debf","ddhceh","hjgecgjjihf","fjjfj","gjceffcedjff","aaaghiabbdagbchhg","dgaieejgbfjdhcbcijfa","cacf","cbjbijehadihhjdi","fiabgiiccbba","eagi","edaagghhehgddic","egcaghjcfci","cjbhgfbdfddh","jbjdhghgjjjacgidfbeg","icjihhgeffbhihhdh","caceeibgbihcfidbig","efij","ahjgfdegbig","cieifgigjdbje","iebahhbjjbjiegaca","bjiheaiaacajdjbdbe","gbgbcdge","bdaijhgefjibfhcedc","gjgfbhhdedhb","hedbgdbajfg","eiebdiiedhagad","a","digcdgjejhf","aaiafddgcefj","dhba","d","ag","bbchdccbgjfbjcgfci","afg","cbjgjfffj","ccgifgeccjgc","jhbgb","dfggiifehbf","hfhedbgjdhfghgdja","fagjbdge","cejhjbccgjggihcgeaf","dbhbfebffgaf","cdhdjhcj","hacjbaifjb","gjejjbbbecidahjched","jadfiigjidfgfa","efjggaebaa","heieaihfffj","ejeafadace","jfajijdifijacbhjc","fig","cehegedcdjcijehaeac","dgjeijfae","dcbeddfdb","gdd","ad","eafdbedfdgeiiif","dagihgebabiahhaf","fiifhdbfhhbb","dfc","eajigcfifjjdhdhjadf","egccbbhgddggebehebhc","debahdfadag","fhhcedjachdcjhd","iaaicjbgejjaihcdaab","jggbiee","gigdjccjficebidhjdcj","fa","aehaghjbfibcd","ceiicfbfgccbfjbi","ieheijg","bhcffafei","ahhihfjja","bdiihbciicfbhead","bebihe","dedigbeddahabdfa","dcehfcbhibiihhg","bdbdaifcbediabebhee","ghbgecjcidafbgfad","bfiaafcbdijj","jffeijjijcbeihgbccfj","fafbbjgagjbdd","jcejgd","dffgjfdibfabjgddcb","acdhaebbiiacffgcdj","ifibah","fighebjccg","jehejgdgiddebehfhdba","ffebafcaafcgadfjai","icjcdgachddebbbhj","hcchjbecfigah","cdjfffgafhie","fedaejjeedahhccg","gdedhijcjh","aaccbgifg","dibffhffigaeegfg","ba","hg","efa","acifijeg","cichjibjdhgajcdc","ccejgejcgabafgb","dhfi","cdgdfcfbhaefbdddh","aebdjf","fdghbigifah","jeegcicbfejc","bebehc","dgfgdhg","jjfbifgfj","gejbebejbfhb","ghfhheeaddc","iedah","hfjchb","hjhdfbjfigdbgbgggf","ebfiecdghcfg","eheiicbhiedi","fjaa","ccbeheejcicahhhj","ebbjbd","hafebbhfgdjbde","aibjfcfe","jbbdbjac","ghdaec","gbabhj","e","fcbjgibadfcdbd","afahbhjjbad","adadeiibjgchihgcdfjh","dgadgfji","ecidcfhajhdfcf","dfeeebggedhfidede","cadejafjaajaicd","ibd","dagf","eigfajhfgdcbhi","ce","jdbgiefed","bgffchfeebhejgh","fj","geddhdghcb","jcabjacjhbiejjaehih","gfchcjceadifii","cgjjaai","jicfgjciciihagd","agcheaeahddheje","jaaiehdhfhcbg","ciafhfadeahiedgac","fdecghgehfdigfa","hajffedb","hcjefhfeggbaafgcheh","fdddcdchbhjgfig","aghggjdcbehgccd","ehfdj","gbbgdb","cdgjifidcdiejfga","gbjdi","fegicbfdhbdeicdac","ijfbegfdjcbjcaddbcgg","hihfjdadifh","fdccdcdchdh","ciciaejifbeefcciigb","afcccc","jfci","fccje","fd","ebbgfdi","bdcig","ehbaji","cbdjiffgfagagjbaid","bejhcgdcaeaaibibca","fhigfbfddfbai","cgbadcgdchia","jh","aahjbj","fddfahebabfajafgceb","cbficcbhadchedcidg","iihjdaedbb","bbigheagfee","ffggehgfificifdejga","acidhghj","bbbggfegjh","bfcbhdied","bejadihbdjid","ajhfgjehaaabb","fhgjjedajhb","jejghcjfbgda","ef","jdcahjiahgfejaacc","fjacgcjjfeb","ggefjf","jfejaah","fbgfhefcegcbbihf","igefidejfgdefjeg","beaecfgjcda","dffccaecieicbcgheeeh","hbfaebjdi","eddgcfjhbh","ieheecbefgjhcjficb","ia","iibfefchhdei","fjdcdccaefi","gebcjejihhdjjbf","jgee","fc","gdfaejbfbgeffffibb","iciabibhihfcejdjggba","bcfhfafj","cchgaibghghjifehgdac","gheheg","fddbc","jfebecgifdajh","bjichcbbdhg","eaeaaadc","febhcc","dba","bafchg","cbjbghgfjij","ae","cihijabjhd","abjb","ijiagdeh","cjhdbbgdfh","ffdgagecchicae","iii","iiejcideejjifiia","dcb","cgjc","eeddie","jedbfgdb","bjiadfef","fddjiejeghhafhjefb","ebdfbfdbgdhcdjddigj","hgiffaigdgehfdb","geggc","beiiagf","gfbhjej","jagadabjcgiceija","gghidjhaeficjej","eibggjgccfbhcad","hhijjeaebbg","chddicbchb","iifgcbed","ehcfbjaeahfbbdjgf","idcdaajf","ch","aigigiehdbiehh","egacbfbejh","chhchhdfccagdidhed","bceefjjjhhjdbj","fgjdcigjaabiccfba","bjhddhjjbbiajbchia","fjbicdcgffadhgbdc","gbgba","ajjdffgegahf","cbjfeeja","egfjbfgghjeaeaaeiej","edhgdgjgfiddfjddd","hc","dhaffehdh","abaca","fdfiajaehcddi","ebddcj","dejh","jjfigabh","bgiijbecafcag","dfffjdabhegej","fgjdjdcjhcidjgbhb","adgaca","jbadfgjfd","ggbdbehbdbjbjif","dbibg","ifdgaihcaaif","hfcicghddi","dfgieidbddhcdbcc","cg","ecajej","cegigiaejcbddgef","j","bbddbjebeccgjbbbifeg","dgffdedej","bcffhigji","bacgeah","cehfdaebaagjecfbfjeh","ffdjbfjigfejheacec","bhicaf","feifehchcf","eh","cc","fgebehacgggaafecahbi","jdjdeg","ceiegifagjdig","egjf","ichajgibiijbffhgjd","hehjhhgjecihiaeh","jjbfbjjgdjijhdefhe","ieijjbabdiggbej","jhggbgcbajigebb","bb","ciigfdigbajfjaajghh","eai","eaggcf","jeefebecid","fhaifhfiiighdgdgicf","fcj","iifd","gbgcaefcf","jjddcjfdchcdchce","ee","hiffa","dcfda","ijcfbbfbae","hcbdhhg","aheifdcjdgbccccjgadc","ecjabjjbdefgjbbhj","bfdjjcfbdfdcef","icjfheiee","hecff","hccccjdgeafdjeebabca","gibiafaihd","idbaahbddg","iieigidefdbddjc","bcahbdifgjcfahcdfea","djadjjgaebcg","idjaeegcc","beehcdcb","ehbfdhffaa","djjff","igacfgdbjiedffia","hdgabjaefahhcibiba","bca","eehfjb","iibfigca","gifgcaafddebajaedjdg","ai","ijgbjcjj","ifdjjjfcabhijjgc","hh","ecfijbhfcajbfc","iifcdifc","ehf","hjfedgdbcdcif","ihhedgbedddegebcfi","gc","cfj","eicigiaacghhfgch","fjcdjadd","idihdhjbfgdjeg","fddbdfigfbgchdcaaehc","cgdfef","idicdcbihehdcdi","bceibi","ifehjcfbihjefaabhcc","ieegghfahacidc","adbicied","ghbcdegb","jeijjbbfgcdgjied","geaad","ajjbccgbifcdggafc","accddbbhjcbhe","fbfb","jifgjhaadchj","hgjhdifeiji","gcjaddjjdgchdbcigch","dghgehia","jdiab","fbad","ebgiaabdbdehjce","hahehbifa","heigfcjigjabhaiedb","cjjc","ffhjbhahegji","bihddjfdbhhigcf","djccffcfjfcdbedcj","jcfbhifieihfibgbbe","jdbgiebfgch","hgfgaibcabf","idbaiaeadficihfd","hihjaadeebegcbddi","cdhahbjgjaifideb","ecficjfgc","dehjdf","fffe","ffeghcihcfab","ahdbee","gfccgc","ahbffbiah","ccg","ecicgiddgebfgcdgi","bc","dbhggffhdbjhhab","idjiijfgi","afch","hcghdedadhhchhjafcji","eaigidgj","bbgb","bghibhjaahcebgf","fjjffj","deaaaaeeheaecj","icjjcdijghgcg","chjh","jgcaahhbdjadfgjcg","gfafjdgh","fdggghejhh","hahhjfidghfbcfjgji","ejg","bjachejdcfjc","ibbbcgdbj","hjjhccggidaie","iiaehchic","jgheggigbbfa","afgieeeafaiaiccaag","eejjfbihhiegbbbgfeh","fjdbcaijfjc","igeedcfegceaajbe","aafjhaeahbedcf","agbcbeifeahh","fafjajddaabefcjdcff","aajfahafedejifaga","hbag","ca","bffdfecaebaghccfbdej","id","cdbbihchhebfi","cjaihigib","bfahhjjjabdeeeje","bhcagbgc","ejjfhebejadiidehdcgb","ecigdhfifbjch","jgagf","bhjdaejfbgjc","hbjfiediiajbej","cjgd","acejgdhecef","jhjdbi","ijidbeaaheaiahjcfcd","gjdfjgcechcjc","bfidjacdhegegibhh","egbefbdbicbid","biga","igebachebcjcecge","dbfciciahgfebecb","ffaig","ebcdeaciibfjefb","hcaibiidejh","hbfebahiid","ffgehig","dfcgidibacf","efdjbchgaihf","abjjhfcah","ijjbeibhedhi"}));
        //System.out.println(new PalindromePairs().palindromePairs(new String[] {"cat","tac"}));
        System.out.println(new PalindromePairs().palindromePairs(new String[] {"ab","ba","abc","cba"}));
    }
}
