package models.services.Check;

import play.libs.F;
import utils.ConfigUtil;
import utils.OptionUtil;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.*;

public class CheckService {

    private final static int TRANSLATION_LENGTH = 3;

    /**
     * 結果取得
     *
     * @param name
     * @return
     */
    public F.Option<String> getResult(String name) {
        List<String> versions = ConfigUtil.getByList("checkYou.setting.answer").getOrElse(new ArrayList<String>() {{
            add("2.1.3 Java");
        }});

        /* 引数nameに基づくユニークな値を取得する */
        String uniqueNum = calculateUniqueNumber(name);
        /* 乱数の生成 シード値付き */
        Random rnd = new Random(Integer.parseInt(uniqueNum, 16));

        int rndValue = rnd.nextInt(versions.size());
        System.out.println(versions.size());
        System.out.println(rndValue);

        return getResultText(name, versions.get(rndValue));
    }

    /**
     * 結果テキストの組み立て
     *
     * @param name
     * @param version
     * @return
     */
    public F.Option<String> getResultText(String name, String version) {
        StringBuilder result = new StringBuilder();
        result.append(name);
        result.append(ConfigUtil.get("checkYou.setting.message.result").getOrElse("-"));
        result.append(version);
        result.append(ConfigUtil.get("checkYou.setting.message.resultSuffix").getOrElse("."));
        return OptionUtil.apply(result.toString());
    }


    /**
     * TwitterIDからハッシュ値を導出
     * @param name
     * @return Digest
     */
    public String calculateUniqueNumber(String name) {
        byte[] tmpValue;
        StringBuilder sb = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(name.getBytes());
            tmpValue = md.digest();
            sb = new StringBuilder();
            for (int i = 0; i < TRANSLATION_LENGTH; i++) {
                String hex = String.format("%02x", tmpValue[i]);
                sb.append(hex);
            }
            System.out.println(tmpValue);
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }
}
