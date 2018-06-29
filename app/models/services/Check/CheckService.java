package models.services.Check;

import play.libs.F;
import utils.ConfigUtil;
import utils.OptionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CheckService {

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
        Collections.shuffle(versions);
        return getResultText(name, versions.get(0));
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
}
