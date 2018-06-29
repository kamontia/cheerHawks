package models.services.Check;

import models.entity.Check;
import models.services.Model.ModelService;

import models.setting.CheckYouSetting;
import play.db.ebean.Model;
import play.libs.F;
import utils.ModelUtil;
import utils.OptionUtil;
import utils.PageUtil;

import java.util.List;


/**
 * Checkモデルのサービスクラス
 */
public class CheckModelService implements ModelService<Check> {
    /**
     * IDで検索する
     *
     * @param id
     * @return
     */
    @Override
    public F.Option<Check> findById(Long id) {
        F.Option<Long> idOps = OptionUtil.apply(id);
        if (idOps.isDefined()) {
            Model.Finder<Long, Check> find = ModelUtil.getFinder(Check.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new F.None<Check>();
    }

    /**
     * 保存する
     *
     * @param entry
     * @return
     */
    @Override
    public F.Option<Check> save(Check entry) {
        F.Option<Check> entryOps = OptionUtil.apply(entry);
        if (entryOps.isDefined()) {
            entry.save();
            if (OptionUtil.apply(entry.getId()).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new F.None<Check>();
    }


    /**
     * ページを取得する
     *
     * @param pageSource
     * @return
     */
    @Override
    public F.Option<List<Check>> findWithPage(Integer pageSource) {
        Integer page = PageUtil.rightPage(pageSource);
        Model.Finder<Long, Check> find = ModelUtil.getFinder(Check.class);

        return OptionUtil.apply(find.order()
                .asc("created")
                .findPagedList(page, CheckYouSetting.LIMIT)
                .getList());
    }

    /**
     * 最大ページ数を取得
     *
     * @return
     */
    public F.Option<Integer> getMaxPage() {
        Model.Finder<Long, Check> find = ModelUtil.getFinder(Check.class);
        return OptionUtil.apply(
                find.order()
                        .asc("created")
                        .findPagedList(0, CheckYouSetting.LIMIT)
                        .getTotalPageCount());


    }
}
