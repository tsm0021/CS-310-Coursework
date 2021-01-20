;; I, Trey Scott McAtee, verify that I have complied with the Academic integrity policy.
(defn member [atm, lizt]
  (cond
    (empty? lizt) false
    (= atm (first lizt)) true
    :else (member atm (rest lizt))
  )
)
