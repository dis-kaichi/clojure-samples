
;; paiza系の問題をClojureでやってた際に色々Tipsが溜まったので書く。


;; 001. コンソールから読み込み
;;      ココらへんは問題のパラメータ読み込みで使う。
(defn read-from-console []
  (def v (read-line)) ;; 文字列として読み込まれる
  (println v))

;; 002. 文字列分割
;;      パラメータが半角スペースで区切られた状態で渡されるので分割する。
;;      clojure.string/splitに関しては毎回打つのは面倒なので、予めrequireしておくと便利。
;;      (require '[clojure.string :as str]) ;; こんな感じにするとstr/splitで使える。
(defn split-sample []
  ;; 半角スペース分割
  (println (clojure.string/split "10 20" #" "))
  ;; カンマ分割
  (println (clojure.string/split "OK,NG" #",")))


;; 003. 整数変換
;;      パラメータは整数であることが多いので、文字列の分割後に整数に変換する。
(defn convert-to-int-sample []
  ;; 変換関数をラムダ式で生成して、マップで各値に割り当て変換する。
  (println (map #(Integer/parseInt %) (clojure.string/split "10 20" #" "))))
