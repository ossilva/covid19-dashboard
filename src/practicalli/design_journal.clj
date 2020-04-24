(ns practicalli.design-journal
  (:require [oz.core :as oz]))


;; Oz server listening on a websocket for views to display.
;; Enables fast feedback for visualization
(oz/start-server!)



;; Data Generation

(defn mock-data-set
  "Generates a set of mock data for each name

  Arguments: names as strings, names used in keys
  Returns: Sequence of maps, each representing confirmed cases"
  [& locations]
  (for [location locations
        day      (range 20)]
    {:day      day
     :location location
     :cases    (+ (Math/pow (* day (count location)) 0.8)
                  (rand-int (count location)))}))

(mock-data-set "England" "Scotland" "Wales" "Northern Ireland")
;; => ({:day 0, :location "England", :cases 1.0}
;;     {:day 1, :location "England", :cases 7.743276393803367}
;;     {:day 2, :location "England", :cases 13.258523872989459}
;;     {:day 3, :location "England", :cases 16.422875300666448}
;;     {:day 4, :location "England", :cases 18.378925219250924}
;;     {:day 5, :location "England", :cases 20.189151347155786}
;;     {:day 6, :location "England", :cases 19.888381054913122}
;;     {:day 7, :location "England", :cases 26.498670948012276}
;;     {:day 8, :location "England", :cases 27.03516289842348}
;;     {:day 9, :location "England", :cases 29.508850275948053}
;;     {:day 10, :location "England", :cases 32.9280507756976}
;;     {:day 11, :location "England", :cases 34.299260572778856}
;;     {:day 12, :location "England", :cases 34.62768266080517}
;;     {:day 13, :location "England", :cases 42.9175705807045}
;;     {:day 14, :location "England", :cases 45.17246133441246}
;;     {:day 15, :location "England", :cases 42.39533859324643}
;;     {:day 16, :location "England", :cases 43.58875032686557}
;;     {:day 17, :location "England", :cases 45.75489563854074}
;;     {:day 18, :location "England", :cases 47.89569020671064}
;;     {:day 19, :location "England", :cases 50.012816499808885}
;;     {:day 0, :location "Scotland", :cases 1.0}
;;     {:day 1, :location "Scotland", :cases 11.278031643091577}
;;     {:day 2, :location "Scotland", :cases 11.18958683997628}
;;     {:day 3, :location "Scotland", :cases 19.71068609258575}
;;     {:day 4, :location "Scotland", :cases 19.000000000000004}
;;     {:day 5, :location "Scotland", :cases 25.127049995800743}
;;     {:day 6, :location "Scotland", :cases 25.13058987556147}
;;     {:day 7, :location "Scotland", :cases 26.03516289842348}
;;     {:day 8, :location "Scotland", :cases 27.85761802547598}
;;     {:day 9, :location "Scotland", :cases 31.610188015018984}
;;     {:day 10, :location "Scotland", :cases 38.30212829607493}
;;     {:day 11, :location "Scotland", :cases 38.940667420160906}
;;     {:day 12, :location "Scotland", :cases 41.53159496449108}
;;     {:day 13, :location "Scotland", :cases 45.07964401264505}
;;     {:day 14, :location "Scotland", :cases 49.58875032686557}
;;     {:day 15, :location "Scotland", :cases 47.06223395648531}
;;     {:day 16, :location "Scotland", :cases 52.50293012833275}
;;     {:day 17, :location "Scotland", :cases 51.91328587177879}
;;     {:day 18, :location "Scotland", :cases 57.29543283815009}
;;     {:day 19, :location "Scotland", :cases 56.65124317675735}
;;     {:day 0, :location "Wales", :cases 0.0}
;;     {:day 1, :location "Wales", :cases 7.623898318388478}
;;     {:day 2, :location "Wales", :cases 8.309573444801934}
;;     {:day 3, :location "Wales", :cases 8.727161387290321}
;;     {:day 4, :location "Wales", :cases 11.98560543306118}
;;     {:day 5, :location "Wales", :cases 14.132639022018838}
;;     {:day 6, :location "Wales", :cases 19.194870523363548}
;;     {:day 7, :location "Wales", :cases 19.189151347155786}
;;     {:day 8, :location "Wales", :cases 22.127049995800743}
;;     {:day 9, :location "Wales", :cases 25.016965485301043}
;;     {:day 10, :location "Wales", :cases 22.86525259636632}
;;     {:day 11, :location "Wales", :cases 28.676874454922782}
;;     {:day 12, :location "Wales", :cases 28.45580618665162}
;;     {:day 13, :location "Wales", :cases 30.205297528345778}
;;     {:day 14, :location "Wales", :cases 33.9280507756976}
;;     {:day 15, :location "Wales", :cases 31.626345475706252}
;;     {:day 16, :location "Wales", :cases 36.30212829607493}
;;     {:day 17, :location "Wales", :cases 36.95707936800063}
;;     {:day 18, :location "Wales", :cases 39.59266228400805}
;;     {:day 19, :location "Wales", :cases 42.210162462449645}
;;     {:day 0, :location "Northern Ireland", :cases 14.0}
;;     {:day 1, :location "Northern Ireland", :cases 19.18958683997628}
;;     {:day 2, :location "Northern Ireland", :cases 29.000000000000004}
;;     {:day 3, :location "Northern Ireland", :cases 26.13058987556147}
;;     {:day 4, :location "Northern Ireland", :cases 35.85761802547598}
;;     {:day 5, :location "Northern Ireland", :cases 44.30212829607493}
;;     {:day 6, :location "Northern Ireland", :cases 52.53159496449108}
;;     {:day 7, :location "Northern Ireland", :cases 46.58875032686557}
;;     {:day 8, :location "Northern Ireland", :cases 58.50293012833275}
;;     {:day 9, :location "Northern Ireland", :cases 57.29543283815009}
;;     {:day 10, :location "Northern Ireland", :cases 60.98237309421565}
;;     {:day 11, :location "Northern Ireland", :cases 69.57633653571948}
;;     {:day 12, :location "Northern Ireland", :cases 70.08740340207163}
;;     {:day 13, :location "Northern Ireland", :cases 77.5238144704248}
;;     {:day 14, :location "Northern Ireland", :cases 77.89242230085388}
;;     {:day 15, :location "Northern Ireland", :cases 84.19900743499228}
;;     {:day 16, :location "Northern Ireland", :cases 92.44850628946526}
;;     {:day 17, :location "Northern Ireland", :cases 94.64517938986725}
;;     {:day 18, :location "Northern Ireland", :cases 95.79273815672464}
;;     {:day 19, :location "Northern Ireland", :cases 110.8944421913114})


;; Line plot
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def line-plot
  "Transform data for visualization"
  {:mark     "line"
   :data     {:values (mock-data-set "England" "Scotland" "Wales" "Northern Ireland")}
   :encoding {:x     {:field "day" :type "quantitative"}
              :y     {:field "cases" :type "quantitative"}
              :color {:field "location" :type "nominal"}}})

;; Notes:
;; The encoding field names match the keywords in the data values
;; TODO: review the available types

;; Send visualization to Oz server
(oz/view! line-plot)


;; Stacked bar
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def stacked-bar
  {:mark     "bar"
   :data     {:values (mock-data-set "England" "Scotland" "Wales" "Norther Ireland")}
   :encoding {:x     {:field "day"
                      :type  "ordinal"}
              :y     {:aggregate "location"
                      :field     "cases"
                      :type      "quantitative"}
              :color {:field "location"
                      :type  "nominal"}}})

(oz/view! stacked-bar)


;; Dashboard
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Define a dashboard using the hiccup syntax, including calls to the two views.


(def dashboard
  [:div
   [:h1 "COVID19 Tracker - Mock data"]
   [:p "Mock data to experiment with types of views"]
   [:div {:style {:display "flex" :flex-direction "row"}}
    [:vega-lite line-plot]
    [:vega-lite stacked-bar]]])

(oz/view! dashboard)

;; Reading in CSV data
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(require '[clojure.java.io :as io])
(require '[clojure.data.csv :as csv])


(def covid-uk-daily-indicators
  (csv/read-csv
    (slurp
      (io/resource "data/daily-indicators.csv"))))


covid-uk-daily-indicators
;; => (["DateVal"
;;      "TotalUKCases"
;;      "NewUKCases"
;;      "TotalUKDeaths"
;;      "DailyUKDeaths"
;;      "EnglandCases"
;;      "EnglandDeaths"
;;      "ScotlandCases"
;;      "ScotlandDeaths"
;;      "WalesCases"
;;      "WalesDeaths"
;;      "NICases"
;;      "NIDeaths"]
;;     ["3/27/2020"
;;      "14,543"
;;      "  2,885"
;;      "759"
;;      "181"
;;      " 12,288"
;;      "    679"
;;      "1,059"
;;      "33"
;;      "921"
;;      "34"
;;      "275"
;;      "13"])

(zipmap (first covid-uk-daily-indicators) (second covid-uk-daily-indicators))
;; => {"TotalUKDeaths" "759", "DailyUKDeaths" "181", "EnglandCases" " 12,288", "EnglandDeaths" "    679", "TotalUKCases" "14,543", "WalesCases" "921", "ScotlandDeaths" "33", "DateVal" "3/27/2020", "NewUKCases" "  2,885", "WalesDeaths" "34", "NICases" "275", "ScotlandCases" "1,059", "NIDeaths" "13"}

(def covid-uk-daily-indicators-map
  (zipmap (first covid-uk-daily-indicators) (second covid-uk-daily-indicators)))



;; Adding daily indicators
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def dashboard-headlines
  [:div
   [:h1 "COVID19 Tracker - Mock data"]
   [:p (str "Headline figures for: " (get covid-uk-daily-indicators-map "DateVal"))]
   [:div {:style {:display "flex" :flex-direction "row"}}
    [:h2 (str "Total UK Cases:"  (get covid-uk-daily-indicators-map "TotalUKCases"))]
    [:h2 (str "England Cases: "  (get covid-uk-daily-indicators-map "EnglandCases"))]
    [:h2 (str "Scotland Cases: "  (get covid-uk-daily-indicators-map "ScotlandCases"))]
    ]
   [:div {:style {:display "flex" :flex-direction "row"}}
    [:vega-lite line-plot]
    [:vega-lite stacked-bar]]])

(oz/view! dashboard-headlines)


;; Improving design with a CSS framework
;; Including CSS from a content delivery network (CDN)

(def dashboard-headlines-bootstrap
  [:div
   [:link {:rel         "stylesheet"
           :href        "https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
           :integrity   "sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
           :crossorigin "anonymous"}]
   [:div {:class "jumbotron"}
    [:h1 {:class "display-4"}
     "COVID19 Tracker - Mock data"]]

   [:h3 (str "Headline figures for: " (get covid-uk-daily-indicators-map "DateVal"))]

   [:div {:style {:display "flex" :flex-direction "row"}}
    [:div {:class "card"}
     [:div {:class "card-title"}
      [:h2 (str "Total UK Cases:"  (get covid-uk-daily-indicators-map "TotalUKCases"))]]]
    [:div {:class "card"}
     [:div {:class "card-title"}
      [:h2 (str "Scotland Cases: "  (get covid-uk-daily-indicators-map "ScotlandCases"))]   ]]
    [:div {:class "card"}
     [:div {:class "card-title"}
      [:h2 (str "England Cases: "  (get covid-uk-daily-indicators-map "EnglandCases"))]]]]

   [:div {:style {:display "flex" :flex-direction "row"}}
    [:vega-lite line-plot]
    [:vega-lite stacked-bar]]])


(oz/view! dashboard-headlines-bootstrap)


;; Using Bulma instead of Bootstrap, as its more developer friendly
;; https://bulma.io/

(def dashboard-headlines-bulma
  [:div
   ;; Web page meta data
   [:link {:rel  "stylesheet"
           :href "https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css"}]

   ;; Web page structure and content

   ;; Heading
   [:section {:class "hero is-dark is-bold"}
    [:div {:class "hero-body"}
     [:div {:class "container"}
      [:h1 {:class "title is-family-primary"}
       "COVID19 Tracker - Mock data"]
      [:h2 {:class "subtitle"}
       "Data will be extracted from "
       [:a {:href "https://www.gov.uk/government/publications/covid-19-track-coronavirus-cases"} "Gov.UK"]]]]]

   ;; Daily Headline figures
   [:section {:class "section has-text-centered"}
    #_[:h1 (str "Headline figures for: " (get covid-uk-daily-indicators-map "DateVal"))]

    ;; UK Totals

    [:div {:class "columns"}

     ;; UK cumulative totals
     [:div {:class "column is-half"}
      [:div {:class "card"}
       [:div {:class "card-content"}
        [:div {:class "title "}
         [:h2 {:class "is-family-primary"}
          "Cumulative UK Totals "]
         [:h1 {:class "has-text-primary is-family-primary"
               :style {:font-size "5rem"}}
          (str (get covid-uk-daily-indicators-map "TotalUKCases"))]
         [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
          "5,373"]]]]]

     ;; UK daily totals
     [:div {:class "column is-half"}
      [:div {:class "card"}
       [:div {:class "card-content"}
        [:div {:class "title"}
         [:h2 {:class "is-family-primary"}
          "Daily Totals"]
         [:h1 {:class "has-text-primary is-family-primary"
               :style {:font-size "5rem"}}
          (str (str (get covid-uk-daily-indicators-map "DailyUKDeaths")))]
         [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
          "439"]]]]]]

    ;; Country Cases

    [:div {:class "columns"}

     ;; Total cases in England
     [:div {:class "column is-one-quarter"}
      [:div {:class "card"}
       [:div {:class "card-content"}
        [:div {:class "title"}
         [:h2 {:class "is-family-primary"}
          "England"]
         [:h1 {:class "has-text-primary is-family-primary"}
          (str (get covid-uk-daily-indicators-map "EnglandCases"))]
         [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
          (get covid-uk-daily-indicators-map "EnglandDeaths")]]]]]

     ;; Total Cases in Scotland
     [:div {:class "column is-one-quarter"}
      [:div {:class "card"}
       [:div {:class "card-content has-text-centered"}
        [:div {:class "title"}
         [:h2 {:class "is-family-primary"}
          "Scotland"]
         [:h1 {:class "has-text-primary is-family-primary"}
          (str (get covid-uk-daily-indicators-map "ScotlandCases"))]
         [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
          (get covid-uk-daily-indicators-map "ScotlandDeaths")]]]]]

     ;; Total Cases in Wales
     [:div {:class "column is-one-quarter"}
      [:div {:class "card"}
       [:div {:class "card-content has-text-centered"}
        [:div {:class "title"}
         [:h2 {:class "is-family-primary"}
          "Wales"]
         [:h1 {:class "has-text-primary is-family-primary"}
          (str (get covid-uk-daily-indicators-map "WalesCases"))]
         [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
          (str (get covid-uk-daily-indicators-map "WalesDeaths"))]]]]]

     ;; Total Cases in Northern Ireland
     [:div {:class "column is-one-quarter"}
      [:div {:class "card"}
       [:div {:class "card-content has-text-centered"}
        [:div {:class "title"}
         [:h2 {:class "is-family-primary"}
          "Northern Ireland"]
         [:h1 {:class "is-family-primary has-text-primary"}
          (str (get covid-uk-daily-indicators-map "NICases"))]
         [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
          (str (get covid-uk-daily-indicators-map "NIDeaths"))]]]]]    ]

    ]
   ;; End of Headline figures

   ;; Oz visualization
   [:section {:class "section"}
    [:vega-lite line-plot]
    [:vega-lite stacked-bar]]])


(oz/view! dashboard-headlines-bulma)



;; Refactor dashboard with section generators
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn headline-card-total
  "Generate a card for headline figures, to be used in an Oz dashboard"
  [totals]
  [:div {:class "column is-half"}
   [:div {:class "card"}
    [:div {:class "card-content"}
     [:div {:class "title "}
      [:h2 {:class "is-family-primary"}
       (:name totals)]
      [:h1 {:class "has-text-primary is-family-primary"
            :style {:font-size "5rem"}}
       (str (get covid-uk-daily-indicators-map (:type totals)))]
      [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
       "5,373"]]]]]
  )


(defn headline-card-country
  "Generate a card for headline figures, to be used in an Oz dashboard"
  [country]
  [:div {:class "column is-one-quarter"}
   [:div {:class "card"}
    [:div {:class "card-content has-text-centered"}
     [:div {:class "title"}
      [:h2 {:class "is-family-primary"}
       (:name country)]
      [:h1 {:class "is-family-primary has-text-primary"}
       (str (get covid-uk-daily-indicators-map (str (:alias country)) "Cases"))]
      [:h2 {:style {:color "hsl(300, 100%, 25%)"}}
       (str (get covid-uk-daily-indicators-map (str (:alias country)) "Deaths"))]]]]]
  )



(def dashboard-headlines-bulma-generators
  [:div
   ;; Web page meta data
   [:link {:rel  "stylesheet"
           :href "https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css"}]

   ;; Web page structure and content

   ;; Heading
   [:section {:class "hero is-dark is-bold"}
    [:div {:class "hero-body"}
     [:div {:class "container"}
      [:h1 {:class "title is-family-primary"}
       "COVID19 Tracker - Mock data"]
      [:h2 {:class "subtitle"}
       "Data will be extracted from "
       [:a {:href "https://www.gov.uk/government/publications/covid-19-track-coronavirus-cases"} "Gov.UK"]]]]]

   ;; Daily Headline figures
   [:section {:class "section has-text-centered"}
    #_[:h1 (str "Headline figures for: " (get covid-uk-daily-indicators-map "DateVal"))]

    ;; UK Totals
    [:div {:class "columns"}
     ;; UK cumulative totals
     (headline-card-total {:name "Cumulative UK Totals " :type "TotalUKCases"})

     ;; UK daily totals
     (headline-card-total {:name "Daily Totals" :type "DailyUKDeaths"})]

    ;; Country Cases
    [:div {:class "columns"}

     ;; Total cases in England
     (headline-card-country {:name "England" :alias "England"})

     ;; Total Cases in Scotland
     (headline-card-country {:name "Scotland" :alias "Scotland"})

     ;; Total Cases in Wales
     (headline-card-country {:name "Wales" :alias "Wales"})

     ;; Total Cases in Northern Ireland
     (headline-card-country {:name "Northern Ireland" :alias "NI"})
     ]
    ]
   ;; End of Headline figures

   ;; Oz visualization
   [:section {:class "section"}
    [:vega-lite line-plot]
    [:vega-lite stacked-bar]]])


(oz/view! dashboard-headlines-bulma-generators)





;; Level not working as shown on the bulma website
;; (def dashboard-bulma-level
;;   [:nav {:class "level"}
;;    [:div {:class "level-item has-text-centered"}
;;     [:p {:class "heading"} "England"]
;;     [:p {:class "title has-text-primary is-size-1"} "12,288"]
;;     [:p {:class "title has-text-primary"} "12,288"]]
;;    [:div {:class "level-item has-text-centered"}
;;     [:p {:class "heading"} "England"]
;;     [:p {:class "title has-text-primary is-size-1"} "12,288"]
;;     [:p {:class "title has-text-primary"} "12,288"]]]
;;   )

;; (oz/view! dashboard-bulma-level)

;; TODO:
;; Set own colours
;; Add some bootstrap to make the headlines look nicer
;; Investigate other graphs


;; Alternative: semantic-csv
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(require '[semantic-csv.core :as semantic])

(def data
  (with-open [in-file (io/reader "resources/data/daily-indicators.csv")]
    (->>
      (csv/parse-csv in-file)
      (semantic/remove-comments)
      (semantic/mappify)
      #_(semantic/cast-with {:this ->int})
      doall)))

(csv/read-csv )

(def covid-uk-daily-indicators-maps
  (semantic/mappify
    (csv/read-csv
      (slurp
        (io/resource "data/daily-indicators.csv")))))

covid-uk-daily-indicators-map

;; As a threading macro

(->> "data/daily-indicators.csv"
     io/resource
     slurp
     csv/read-csv
     semantic/mappify
     )
;; => ({:TotalUKCases "14,543", :EnglandDeaths "    679", :ScotlandCases "1,059", :DateVal "3/27/2020", :NIDeaths "13", :TotalUKDeaths "759", :DailyUKDeaths "181", :NewUKCases "  2,885", :WalesCases "921", :WalesDeaths "34", :NICases "275", :ScotlandDeaths "33", :EnglandCases " 12,288"})



(def covid-uk-daily-indicators-map-converted
  (->> "data/daily-indicators.csv"
       io/resource
       slurp
       csv/read-csv
       semantic/mappify
       semantic/->long )
  )
