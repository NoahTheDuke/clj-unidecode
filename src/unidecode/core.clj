(ns unidecode.core
  (:gen-class))

(def cache-atom (atom {}))

(defn- load-decode-map
  ([code-point]
   (if (contains? @cache-atom code-point)
     (@cache-atom code-point)
     (let [classname (symbol (format "unidecode.maps.x%03x" code-point))]
       (try
         (require classname)
         (let [data (var-get (ns-resolve classname 'data))]
           (swap! cache-atom assoc code-point data))
         (catch java.io.FileNotFoundException e
           (swap! cache-atom assoc code-point nil)))
       (@cache-atom code-point)))))

(defn unidecode
  "Strips `string` of diacritics and returns the ASCII representation."
  [string]
  (apply str (for [chr (seq string)
                   :let [codepoint (int chr)
                         section (bit-and (bit-shift-right codepoint 8) 0xff)
                         position (bit-and codepoint 0xff)]]
               (when-let [table (load-decode-map section)]
                 (table position)))))
