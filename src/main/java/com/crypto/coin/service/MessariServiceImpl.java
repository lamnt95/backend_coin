package com.crypto.coin.service;

import com.crypto.coin.model.Post;
import com.crypto.coin.repository.PostRepo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MessariServiceImpl implements MessariService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ThirdPartyAPI thirdPartyAPI;

    private String url = "https://graphql.messari.io/query";

    private String getBodyApiMessariList(Long limit) {
        String a = "{\n" + "    \"operationName\": \"ResearchCategoryArticles\",\n" + "    \"variables\": {\n" + "        \"limit\": " + limit + ",\n" + "        \"tags\": []\n" + "    },\n" + "    \"query\": \"query ResearchCategoryArticles($limit: Int = 5, $tags: [String!] = [], $after: PaginationCursor) {\\n  articles(after: $after, first: $limit, where: {published: true, tags_in: $tags}) {\\n edges {\\n node {\\n  articleType\\n  id\\n   content\\n  updateDate\\n publishDate\\n slug\\n title\\n }\\n }\\n   pageInfo {\\n  hasNextPage\\n }\\n  }\\n}\\n\"\n" + "}";
        JsonElement b = new Gson().fromJson(a, JsonElement.class);
        return b.toString();
    }

    private List<JsonElement> getResApiMessariList(Response res) throws IOException {
        String res1 = res.body().string();
        JsonElement res2 = new Gson().fromJson(res1, JsonElement.class);
        JsonElement res3 = res2.getAsJsonObject().get("data").getAsJsonObject().get("articles").getAsJsonObject().get("edges");
        Type typeListJson = new TypeToken<List<JsonElement>>() {
        }.getType();
        List<JsonElement> res4 = new Gson().fromJson(res3, typeListJson);
        return res4;
    }

    private String getBodyApiMessariDetail(String slug) {
        String a1 = "{\n" + "  \"operationName\":\"ResearchArticleBySlug\",\n" + "  \"variables\":{\"slug\":\"the-evolution-sushiswap-to-sushi-com\"},\n" + "  \"query\":\"query ResearchArticleBySlug($slug: String!) {\\n  articleBySlug(slug: $slug) {\\n    articleType\\n    assets {\\n symbol\\n }\\n content\\n id\\n    publishDate\\n    slug\\n    title\\n    updateDate\\n }\\n}\\n\"\n" + "}";
        JsonElement b1 = new Gson().fromJson(a1, JsonElement.class);
        JsonElement c1 = b1.getAsJsonObject().get("variables");
        c1.getAsJsonObject().addProperty("slug", slug);
        return b1.toString();
    }

    private JsonElement getResApiMessariDetail(Response resit1) throws IOException {
        String resit2 = resit1.body().string();
        JsonElement resit3 = new Gson().fromJson(resit2, JsonElement.class);
        JsonElement resit4 = resit3.getAsJsonObject().get("data").getAsJsonObject().get("articleBySlug").getAsJsonObject();
        return resit4;
    }

    private String getFieldStr(JsonElement a, String field) {
        return a.getAsJsonObject().get(field).getAsString();
    }

    private JsonElement getFieldJson(JsonElement a, String field) {
        return a.getAsJsonObject().get(field);
    }

    @Override
    public void fetch(Long limit) throws IOException {
        List<Post> ents = new ArrayList<>();

        List<String> ids = postRepo.getListIdMessari();

        String body1 = getBodyApiMessariList(limit);

        log.info("START api list");
        Response res = thirdPartyAPI.postWithoutToken(url, body1, null, null);

        int stt = res.code();
        if (stt >= 200 && stt <= 300) {
            log.info("SUCCESS");
            List<JsonElement> resJson = getResApiMessariList(res);
            if (resJson != null && resJson.size() > 0) {
                Long index = 0l;
                for (JsonElement it : resJson) {
                    index += 1l;
                    JsonElement it1 = getFieldJson(it, "node");
                    if (it1 != null) {
                        Post ent = new Post();
                        ent.setSource("messari");
                        String srcId = getFieldStr(it1, "id");
                        ent.setSrcId(srcId);
                        ent.setName(getFieldStr(it1, "title"));
                        ent.setArticleType(getFieldStr(it1, "articleType"));
                        String slug = getFieldStr(it1, "slug");
                        ent.setSlug(slug);
                        String link = "https://messari.io/article/" + slug;
                        ent.setLink(link);
                        String date = getFieldStr(it1, "publishDate");
                        if (date != null) {
//                            Date currentDate = new Date(Long.parseLong(date) * 1000);
//                            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//                            String dateStr = dateFormat.format(currentDate);
                            ent.setDate(date);
                        }

                        if (ids == null || (ids != null && ids.size() == 0) || (ids != null && ids.size() > 0 && !ids.contains(srcId))) {
//                            log.info("ADDED item " + slug);
                            ents.add(ent);
                        }
                    }
                }
                log.info("ADDED total " + ents.size());
            }
        }

        if (ents.size() > 0) {
            Long index = 1l;
            for (Post ent : ents) {
                String slug = ent.getSlug();
                if (slug != null) {
                    String body = getBodyApiMessariDetail(slug);
                    log.info("START api detail " + slug + " - " + index);
                    Response res2 = thirdPartyAPI.postWithoutToken(url, body, null, null);
                    int stt2 = res2.code();
                    if (stt2 >= 200 && stt2 <= 300) {
                        log.info("Call api SUCCESS");
                        JsonElement dt = getResApiMessariDetail(res2);
                        String md = getFieldStr(dt, "content");
                        ent.setMarkdown(md);
                        index += 1;
                        try {
                            postRepo.saveAndFlush(ent);
                            log.info("Insert DB SUCCESS");
                        } catch (Exception e) {
                            log.info("ERR");
                        }
                    }else{
                        log.info("Call api ERROR");
                    }
                }
            }
            log.info("the end");
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> ps =  postRepo.findAll();
        return ps;
    }
    
    @Override
    public String getAllStr() {
        List<Post> ps =  postRepo.findAll();
        Gson gson = new Gson();
        String json = gson.toJson(ps);
        return json;
    }
    
    @Override
    public String getCache() {
        String urlredis = "https://express-simple-hqn6en--3010.local.webcontainer.io/getCache";
        Response res2 = thirdPartyAPI.getWithoutToken(urlredis, body, null, null);
        int stt2 = res2.code();
        if (stt2 >= 200 && stt2 <= 300) {
            log.info(stt2);
            log.info(res2.body().toString());
            return res2.body().toString();
        }
        return null;
    }
}
