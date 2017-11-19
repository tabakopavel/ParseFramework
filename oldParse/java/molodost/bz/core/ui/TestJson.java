package molodost.bz.core.ui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import molodost.bz.core.object.News;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static javafx.css.StyleOrigin.USER_AGENT;

public class TestJson {

    public static void main(String[] args) throws Exception {

        int newsGroup = 0;

        while (true) {
            TestJson http = new TestJson();
            for (News news : getNews(http.sendGet("https://platform.molodost.bz/api/mongo/posts?mode=new&offset=" + newsGroup))) {
                System.out.println(news.getTitle());
                System.out.println(news.getUserId());
                System.out.println(news.getNewsLink());
                System.out.println(news.getText());
                System.out.println(news.getAuthorName());
                System.out.println(news.getAuthorLink());
                System.out.println("__________________________________________");
            }
            newsGroup++;
        }


    }

    public static String getString() {
        String json = "{\n" +
                "  \"status\": 200,\n" +
                "  \"result\": {\n" +
                "    \"posts\": [\n" +
                "      {\n" +
                "        \"_id\": \"5a09805c6587de774ea11055\",\n" +
                "        \"userId\": \"599402447ae84469de9dd0fb\",\n" +
                "        \"created\": \"2017-11-13T11:22:04.075Z\",\n" +
                "        \"votable\": false,\n" +
                "        \"pinned\": false,\n" +
                "        \"weight\": 0,\n" +
                "        \"programs\": [\n" +
                "          {\n" +
                "            \"_id\": 3,\n" +
                "            \"title\": \"Общая лента\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"_id\": 10,\n" +
                "            \"title\": \"ЦЕХ 25\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"attachments\": [],\n" +
                "        \"comments\": [],\n" +
                "        \"likes_count\": 0,\n" +
                "        \"content\": \"Важное и главное на сегодня расставить приоритеты по задачам всего дня и делегировать и разгрузить себя для решения более важных и срочных вопросов .\\nРаботаем.\",\n" +
                "        \"title\": \"ЦЕХ 25. ДЕНЬ 35 ДЕНЬ\",\n" +
                "        \"id\": \"5a09805c6587de774ea11055\",\n" +
                "        \"poll\": false,\n" +
                "        \"liked\": false,\n" +
                "        \"voted\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"5a097fe1a022b26f920b34a9\",\n" +
                "        \"userId\": \"59d9315c467f8f0976e984d5\",\n" +
                "        \"created\": \"2017-11-13T11:20:01.459Z\",\n" +
                "        \"votable\": false,\n" +
                "        \"pinned\": false,\n" +
                "        \"weight\": 0,\n" +
                "        \"programs\": [\n" +
                "          {\n" +
                "            \"_id\": 3,\n" +
                "            \"title\": \"Общая лента\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"_id\": 10,\n" +
                "            \"title\": \"ЦЕХ 25\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"attachments\": [\n" +
                "          {\n" +
                "            \"_id\": \"5a097fe1a022b26f920b34aa\",\n" +
                "            \"path\": \"https://bm-platform.s3.eu-central-1.amazonaws.com/Sy_0y-Pkf-20171110_193326.jpg\",\n" +
                "            \"mime\": \"image/jpeg\",\n" +
                "            \"name\": \"20171110_193326.jpg\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"comments\": [],\n" +
                "        \"likes_count\": 0,\n" +
                "        \"content\": \"Пока сдавали домашку, мы были первыми и поэтому осталось достаточного свободного времени) мы общались с ребятами из других десяток.  Все такие интересные!) Вообще очень здорово знакомиться с новыми и интересными людьми, прям кайфую от этого.  Интересный контент был от Миши с Петей,  прям крутой, я прониклась) \",\n" +
                "        \"title\": \"Ден 36 (11 ноября)\",\n" +
                "        \"id\": \"5a097fe1a022b26f920b34a9\",\n" +
                "        \"poll\": false,\n" +
                "        \"liked\": false,\n" +
                "        \"voted\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"5a097f3bed0ad16f7b597b51\",\n" +
                "        \"userId\": \"59cfaf985db1103785fafda2\",\n" +
                "        \"created\": \"2017-11-13T11:17:15.010Z\",\n" +
                "        \"votable\": false,\n" +
                "        \"pinned\": false,\n" +
                "        \"weight\": 0,\n" +
                "        \"programs\": [\n" +
                "          {\n" +
                "            \"_id\": 3,\n" +
                "            \"title\": \"Общая лента\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"_id\": 10,\n" +
                "            \"title\": \"ЦЕХ 25\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"attachments\": [],\n" +
                "        \"comments\": [],\n" +
                "        \"likes_count\": 0,\n" +
                "        \"content\": \"Приводила в порядок дом после своего отсутствия на выходных. Обрабатывала заявки которые висят, и одна доставка-продажа. Заработано 1810\",\n" +
                "        \"title\": \"13.11. дом\",\n" +
                "        \"id\": \"5a097f3bed0ad16f7b597b51\",\n" +
                "        \"poll\": false,\n" +
                "        \"liked\": false,\n" +
                "        \"voted\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"5a097ed0ef594319a106a39a\",\n" +
                "        \"userId\": \"59cfaf985db1103785fafda2\",\n" +
                "        \"created\": \"2017-11-13T11:15:28.125Z\",\n" +
                "        \"votable\": false,\n" +
                "        \"pinned\": false,\n" +
                "        \"weight\": 0,\n" +
                "        \"programs\": [\n" +
                "          {\n" +
                "            \"_id\": 3,\n" +
                "            \"title\": \"Общая лента\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"_id\": 10,\n" +
                "            \"title\": \"ЦЕХ 25\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"attachments\": [],\n" +
                "        \"comments\": [],\n" +
                "        \"likes_count\": 0,\n" +
                "        \"content\": \"12,11 Попала на крупную сумму в точку минус Б в результате ДТП, теперь без руля.\\nНожками-ножками бегать буду\\n\\nпервая половину ЦЕХа была как раз про мою ситуацию - про реальность и действительность ) \\nВторая - супер, особенно про вариативность нашего будущего - куда мы идем\\n\",\n" +
                "        \"title\": \"12.11.13 жизнь учит\",\n" +
                "        \"id\": \"5a097ed0ef594319a106a39a\",\n" +
                "        \"poll\": false,\n" +
                "        \"liked\": false,\n" +
                "        \"voted\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"5a097e72e75cef6fffbe42ec\",\n" +
                "        \"userId\": \"59d78cf409395c7b32e3c00b\",\n" +
                "        \"created\": \"2017-11-13T11:13:54.367Z\",\n" +
                "        \"votable\": false,\n" +
                "        \"pinned\": false,\n" +
                "        \"weight\": 0,\n" +
                "        \"programs\": [\n" +
                "          {\n" +
                "            \"_id\": 3,\n" +
                "            \"title\": \"Общая лента\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"_id\": 10,\n" +
                "            \"title\": \"ЦЕХ 25\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"attachments\": [],\n" +
                "        \"comments\": [],\n" +
                "        \"likes_count\": 0,\n" +
                "        \"content\": \"Только что поняла, что отчёт написанный ночью на эмоциях не сохранился, что ж, возможно оно и к лучшему.\\nНапряжение и непонимание внутри 10ки достигло апогея - результатом стал выход половины участников из чата 10-ки.\\nИнсайт: не все используют данный им «второй шанс» грамотно. И выходить из своей кажимости подчас не удается. Мешает эго.\\nФакт: 0\u20BD\\nСпорт: тренировка в зале\\n\\n\",\n" +
                "        \"title\": \"День 36. Чужая реальность\",\n" +
                "        \"id\": \"5a097e72e75cef6fffbe42ec\",\n" +
                "        \"poll\": false,\n" +
                "        \"liked\": false,\n" +
                "        \"voted\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"5a097e309f02fd77766c5337\",\n" +
                "        \"userId\": \"59cfaf985db1103785fafda2\",\n" +
                "        \"created\": \"2017-11-13T11:12:48.168Z\",\n" +
                "        \"votable\": false,\n" +
                "        \"pinned\": false,\n" +
                "        \"weight\": 0,\n" +
                "        \"programs\": [\n" +
                "          {\n" +
                "            \"_id\": 3,\n" +
                "            \"title\": \"Общая лента\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"_id\": 10,\n" +
                "            \"title\": \"ЦЕХ 25\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"attachments\": [],\n" +
                "        \"comments\": [],\n" +
                "        \"likes_count\": 0,\n" +
                "        \"content\": \"Наверстывала домашку 10.11 \\n\\n11.11 Провела мастер-класс по детской ароматерапии, Отлично. Заработала 3700, маржа 2180, плюс заказ\",\n" +
                "        \"title\": \"10.11 и 11.11\",\n" +
                "        \"id\": \"5a097e309f02fd77766c5337\",\n" +
                "        \"poll\": false,\n" +
                "        \"liked\": false,\n" +
                "        \"voted\": false\n" +
                "      },\n" +
                "      {\n" +
                "        \"_id\": \"5a097d613b1f49193a14dd95\",\n" +
                "        \"userId\": \"5994cc82996d7c221a69b8a0\",\n" +
                "        \"created\": \"2017-11-13T11:09:21.436Z\",\n" +
                "        \"votable\": false,\n" +
                "        \"pinned\": false,\n" +
                "        \"weight\": 0,\n" +
                "        \"programs\": [\n" +
                "          {\n" +
                "            \"_id\": 3,\n" +
                "            \"title\": \"Общая лента\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"_id\": 10,\n" +
                "            \"title\": \"ЦЕХ 25\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"attachments\": [],\n" +
                "        \"comments\": [],\n" +
                "        \"likes_count\": 0,\n" +
                "        \"content\": \"\\nЧСС подали заявку на тендер.\\nОтправил 3 КП муниципальным администрациям\\nЧМСЛ обзвонить еще 5 администраций\\nЧСЗ смс рассылку запущу на 2000 смс\\nЛиды 1\\nЗаявок 2\\nДенег 17000\",\n" +
                "        \"title\": \"13.11.17\",\n" +
                "        \"id\": \"5a097d613b1f49193a14dd95\",\n" +
                "        \"poll\": false,\n" +
                "        \"liked\": false,\n" +
                "        \"voted\": false\n" +
                "      }\n" +
                "    ],\n" +
                "    \"polls\": {},\n" +
                "    \"total\": 162511,\n" +
                "    \"votes\": {},\n" +
                "    \"replies\": {},\n" +
                "    \"comments\": {\n" +
                "      \"5a09805c6587de774ea11055\": [],\n" +
                "      \"5a097fe1a022b26f920b34a9\": [],\n" +
                "      \"5a097f3bed0ad16f7b597b51\": [],\n" +
                "      \"5a097ed0ef594319a106a39a\": [],\n" +
                "      \"5a097e72e75cef6fffbe42ec\": [],\n" +
                "      \"5a097e309f02fd77766c5337\": [],\n" +
                "      \"5a097d613b1f49193a14dd95\": []\n" +
                "    },\n" +
                "    \"users\": {\n" +
                "      \"599402447ae84469de9dd0fb\": {\n" +
                "        \"goal\": {\n" +
                "          \"a\": 40000,\n" +
                "          \"b\": 160000\n" +
                "        },\n" +
                "        \"occupation\": \"Производство и продажа кофе в зернах B2C \",\n" +
                "        \"secondary\": [\n" +
                "          \"B2B\",\n" +
                "          \"Производство и продажа кофе в зернах B2C \"\n" +
                "        ],\n" +
                "        \"_id\": \"599402447ae84469de9dd0fb\",\n" +
                "        \"first_name\": \"Антон\",\n" +
                "        \"last_name\": \"Третьяков \",\n" +
                "        \"picture_small\": \"https://bm-platform.s3.eu-central-1.amazonaws.com/rkKcj-L3Z-IMG_20171001_185503_901.jpg\",\n" +
                "        \"name\": \"13625629\"\n" +
                "      },\n" +
                "      \"5994cc82996d7c221a69b8a0\": {\n" +
                "        \"goal\": {\n" +
                "          \"a\": 30000,\n" +
                "          \"b\": 300000\n" +
                "        },\n" +
                "        \"occupation\": \"Кадастровые и геодезические работы\",\n" +
                "        \"secondary\": [\n" +
                "          \"Кадастровые и геодезические работы\"\n" +
                "        ],\n" +
                "        \"_id\": \"5994cc82996d7c221a69b8a0\",\n" +
                "        \"first_name\": \"Виталий\",\n" +
                "        \"last_name\": \"Кокорин\",\n" +
                "        \"picture_small\": \"http://static.molodost.bz/thumb/160_160_2/img/avatars/25854721.jpeg\",\n" +
                "        \"name\": \"25854721\"\n" +
                "      },\n" +
                "      \"59cfaf985db1103785fafda2\": {\n" +
                "        \"goal\": {\n" +
                "          \"a\": 15000,\n" +
                "          \"b\": 45000\n" +
                "        },\n" +
                "        \"occupation\": \"Аромамаркетинг.Аромамассаж+обучение,партнерка.Аромадизайн.Ароматерапия\",\n" +
                "        \"secondary\": [\n" +
                "          \"Добавки в топливо Roil Platinum - остатки товара продаем. Поставок нет\",\n" +
                "          \"Деревянные спорткомплесы \\\"К\\\" детям. Изготовление. Монтаж.\",\n" +
                "          \"Детская ароматерапия. Аромадиагностика ITOVI.\",\n" +
                "          \"Аромамаркетинг.Аромамассаж+обучение,партнерка.Аромадизайн.Ароматерапия\"\n" +
                "        ],\n" +
                "        \"_id\": \"59cfaf985db1103785fafda2\",\n" +
                "        \"first_name\": \"Анастасия\",\n" +
                "        \"last_name\": \"Круглова\",\n" +
                "        \"picture_small\": \"http://static.molodost.bz/thumb/160_160_2/img/avatars/29055287.jpeg\",\n" +
                "        \"name\": \"29055287\"\n" +
                "      },\n" +
                "      \"59d78cf409395c7b32e3c00b\": {\n" +
                "        \"goal\": {\n" +
                "          \"a\": 50000,\n" +
                "          \"b\": 300000\n" +
                "        },\n" +
                "        \"occupation\": \"Лазертаг-клуб, организация спортивно-развлекательных мероприятий\",\n" +
                "        \"secondary\": [\n" +
                "          \"Интернет-магазин Чаёчек.ру\",\n" +
                "          \"Лазертаг-клуб, организация спортивно-развлекательных мероприятий\"\n" +
                "        ],\n" +
                "        \"_id\": \"59d78cf409395c7b32e3c00b\",\n" +
                "        \"first_name\": \"Маргарита\",\n" +
                "        \"last_name\": \"Стальная\",\n" +
                "        \"picture_small\": \"https://bm-platform.s3.eu-central-1.amazonaws.com/H1sB3eL3Z-IMG_2739.JPG\",\n" +
                "        \"name\": \"29974585\"\n" +
                "      },\n" +
                "      \"59d9315c467f8f0976e984d5\": {\n" +
                "        \"goal\": {\n" +
                "          \"a\": 100000,\n" +
                "          \"b\": 400000\n" +
                "        },\n" +
                "        \"occupation\": \"Агентство загородной недвижимости\",\n" +
                "        \"secondary\": [\n" +
                "          \"Агентство загородной недвижимости\"\n" +
                "        ],\n" +
                "        \"_id\": \"59d9315c467f8f0976e984d5\",\n" +
                "        \"first_name\": \"Ксения\",\n" +
                "        \"last_name\": \"Абросимова\",\n" +
                "        \"picture_small\": \"http://static.molodost.bz/thumb/160_160_2/img/avatars/a887d187d73f251e995fbabf28ffda48c066b9df.jpeg\",\n" +
                "        \"name\": \"1228229\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        return json;
    }

    public static List<News> getNews(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        List<News> newsList = new ArrayList<News>();
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject(); // get all json

            JsonElement resultElement = jsonObject.get("result");//get element result

            JsonObject resultObject = resultElement.getAsJsonObject();//get post object

            JsonElement posts = resultObject.get("posts"); // get element post
            JsonElement users = resultObject.get("users"); // get element post

            JsonArray postsArray = posts.getAsJsonArray();

            for (JsonElement element : postsArray) {
                News news = new News();
                news.setTitle(element.getAsJsonObject().get("title").toString().replace("\"", ""));
                news.setText(element.getAsJsonObject().get("content").toString().replace("\\n", " "));
                news.setNewsLink("https://platform.molodost.bz/posts/" + element.getAsJsonObject().get("id").toString().replace("\"", ""));
                news.setUserId(element.getAsJsonObject().get("userId").toString().replace("\"", ""));


                for (String key : users.getAsJsonObject().keySet()) {
                    if (key.equalsIgnoreCase(news.getUserId())) {
                        JsonElement jsonElement1 = users.getAsJsonObject().get(news.getUserId());
                        JsonObject userInfo = jsonElement1.getAsJsonObject();
                        news.setAuthorLink("https://platform.molodost.bz/@" + userInfo.get("name").toString().replace("\"", ""));
                        news.setAuthorName(userInfo.get("first_name").toString().replace("\"", "") + " " + userInfo.get("last_name").toString().replace("\"", ""));


                    }
                }
                newsList.add(news);
            }
        }
        return newsList;
    }


    private String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
