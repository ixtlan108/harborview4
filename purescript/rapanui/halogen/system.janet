(import /html/button :as btn)
(import /html/wrapper :as wr)
(import /html/input :as inp)
(import /html/common :as c)
(import /html/select :as se)


(def prelude 
  @[[1 "module Rapanui.UI"]
    [2 "where"]
    [1 ""]
    [1 "import Prelude"]
    [1 "import Data.Maybe (Maybe(..))"]
    [1 "import Data.Array ((:))"]
    [1 "import DOM.HTML.Indexed.InputType (InputType(..))"]
    [1 "import Halogen.HTML as HH"]
    [1 "import Halogen.HTML.Events as HE"]
    [1 "import Halogen.HTML.Properties as HP"]
    [1 "import Halogen.HTML.Events as HE"]
    [1 "import Halogen.HTML (HTML, ClassName(..))"]
    [1 "import Rapanui.Common (MainAction(..))"]
    [1 ""]])



(def btn-class "ps-mr-1 ps-btn btn btn-outline-success")

(def buttons 
  @[{ :evt "FetchPurchases"
      :title "Fetch Purchases"
      :disabled "false"
      :class btn-class
      :name "fetchPurchases"}
    { :evt "(Timer true)"
      :title "Start Timer"
      :disabled "false"
      :class btn-class
      :name "startTimer"}
    { :evt "(Timer false)"
      :title "Stop Timer"
      :disabled "false"
      :class btn-class
      :name "stopTimer"}])

(def label-class "ps-label ps-mr-1")

(def inputs
  @[{ :name "inpInterval"
      :type :num
      :title "Interval"
      :evt "IntervalChange" 
      :lc label-class
      :disabled false 
      :p1 ["val" "Maybe Int"]}
    { :name "inpTick"
      :type :int
      :title "Tick"
      :lc label-class
      :disabled false 
      :p1 ["val" "Maybe Int"]}])

(def main-action "MainAction")

(def fname "/home/rcs/opt/java/harborview3/purescript/rapanui/src/Rapanui/UI.purs")

(defn mk-output-fn1 [&opt f]
  (if f 
     (partial c/write-result f)
     c/prn-result))

(defn mk-output-fn2 [&opt f]
  (if f
    (let [wr-fn (partial c/write-result f)]
      (fn [b] (map wr-fn b)))
    (fn [b] (map c/prn-result b))))

(defn run []
  (let [f (file/open fname :w)
        out-2 (mk-output-fn2 f)
        out-1 (mk-output-fn1 f)]
    (map out-1 prelude)
    (btn/run buttons main-action out-2)
    (inp/run inputs main-action out-2)
    (file/close f)))


#(map c/prn-result (se/mk-option main-action))
