;; ループ系サンプル

;; XX. 速度計測用関数
;;     http://qiita.com/FScoward/items/467c8a2b50ab7f34f4f9
(defn measure-time [func]
  (let [start (java.lang.System/currentTimeMillis)]
    (func)
    (- (java.lang.System/currentTimeMillis) start)))

;; 01. while
;; 001. whileループ(速い)
;;      clojureの変数は変更不可(Immutable)なので
;;      他のLISPみたいに変数を変更する場合はatomを使って行う
(defn while-sample []
  (def i (atom 0))
  (while (< @i 10000000)
    ;; (println @i)
    (swap! i inc)))

;; 002. whileループ(遅い)
;;      変数を再定義することによってもできるが、上に比べると遅い
;;      (measure-time while-sample)   ;; 1174 (msec)
;;      (measure-time while-sample2)  ;; 2880 (msec)
(defn while-sample2 []
  (def i 0)
  (while (< i 10000000)
    ;; (println i)
    (def i (inc i))))

;; 003. 値の更新
;;      swap!意外にもreset!を使って値を更新する。
;;      atom系の話は下記リンクを参考。
;;      https://gist.github.com/kohyama/6076544
;;      http://www.slideshare.net/sohta/introduction-tostm-withclojure
(defn while-update-sample []
  (def my-value (atom 0))
  (def my-hash (atom (hash-map)))
  (def i (atom 0))
  (while (< @i 10)
    (reset! my-value (+ @my-value @i))
    (swap! my-hash assoc @i "VALUE")
    (swap! i inc))
  (println @my-value) ;; 0から9までの和
  (println @my-hash)) ;; キーが0から9で値が"VALUE"の辞書

;; 02. loop
;; 001. loop(基本)
;;      基本的にこっちのほうが速いので、使えるならばこっちを使うと良い。
(defn loop-sample []
  (loop [i 0]
    (if (< i 10000000)
      (recur (inc i)))))

;; 002. 値の更新
;;      こっちは値の更新というか、次の反復処理に値を渡す感じで進める。
(defn loop-update-sample []
  (loop [i 0
           my-value 0
           my-hash (hash-map)]
    (if (< i 10)
      (recur (inc i) (+ my-value i)
             (assoc my-hash i "VALUE"))
      (do
        (println my-value)
        (println my-hash)))))

