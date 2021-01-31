#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'retrogradeorbit/bootleg "0.1.9")
(require '[clojure.edn :as edn]
         '[clojure.string :as s]
         '[pod.retrogradeorbit.bootleg.utils :as u]
)

(defn convert-to-html
  [site]
  (spit
   "index.html"
   (-> "data.edn"
       slurp
       edn/read-string
       site
       (u/convert-to :html))))
  

(defn site
  [data]
  [[:html 
    [:head
     [:meta {:charset "UTF-8"}]
     [:title "Hello world"]
     [:meta {:name "viewport" :content "width=device-width, intiail-scale=1.0"}]
     [:link :rel "stylesheet" {:href "https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"}]
     [:link {:href "https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" :rel "stylesheet"}]
     ]
    [:body
     [:header.box.text-lg "Hello world"]
     [:ul
 (for [{:keys [name location country]} (:people data)]
   [:li (s/join ", " [name location country])])]
     ]
    ]
   ])

(convert-to-html site)