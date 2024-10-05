import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";

import "./Header.scss";
import { useState } from "react";

export const Header = () => {
  const { t, i18n } = useTranslation();
  const [query, setQuery] = useState("");

  const changeLanguage = (lng: "en" | "ua") => {
    i18n.changeLanguage(lng); // Change the language dynamically
  };

  return (
    <div className="header">
      <div className="header__linkBlock">
        <Link to="/" className="header__linkBlock--link logo">
          Tripsters
        </Link>

        <Link to="/" className="header__linkBlock--link">
          {t("header_homepage")}
        </Link>

        <Link to="/trips" className="header__linkBlock--link">
          {t("header_yourTrips")}
        </Link>

        <Link to="/profile" className="header__linkBlock--link">
          {t("header_profile")}
        </Link>

        <input
          type="text"
          placeholder={t("header_search")}
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
      </div>

      <div className="header__block">
        <div className="header__block--lang">
          <p
            className="header__block--lang--option"
            onClick={() => changeLanguage("en")}
            style={{ cursor: "pointer" }}
          >
            EN
          </p>
          <p className="header__block--lang--option">/</p>
          <p
            className="header__block--lang--option"
            onClick={() => changeLanguage("ua")}
            style={{ cursor: "pointer" }}
          >
            UA
          </p>
        </div>

        <div className="header__block--sign" onClick={() => {}}>
          <p>{t("header_sign")}</p>
        </div>
      </div>
    </div>
  );
};
