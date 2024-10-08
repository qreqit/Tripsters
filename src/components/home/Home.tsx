import { useTranslation } from "react-i18next";
import picAbout1 from "../../pics/home-about1.jpg";
import picAbout2 from "../../pics/home-about2.jpg";
import picHelp1 from "../../pics/home_help1.jpg";
import picHelp2 from "../../pics/home_help2.jpg";
import picHelp3 from "../../pics/home_help3.jpg";
import picHome from "../../pics/home_mission.jpg";

import "./Home.scss";
import { Link } from "react-router-dom";

export const Home = () => {
  const { t } = useTranslation();

  return (
    <main className="home">
      <section className="home__welcome">
        <h1 className="home__welcome--text">{t("home_welcome")}</h1>
      </section>

      <section className="home__about">
        <h2 className="home__about--header">{t("home_about_header")}</h2>

        <img
          src={picAbout1}
          alt="Travelling with friends"
          className="home__about--pic about-pic1"
        />

        <div className="home__about-block info-block1">
          <h4 className="home__about-block--header">
            {t("home_about_block1_header")}
          </h4>

          <p className="home__about-block--text">
            {t("home_about_block1_text")}
          </p>
        </div>

        <img
          src={picAbout2}
          alt="Travelling with friends"
          className="home__about--pic about-pic2"
        />

        <div className="home__about-block info-block2">
          <h4 className="home__about-block--header">
            {t("home_about_block2_header")}
          </h4>

          <p className="home__about-block--text">
            {t("home_about_block2_text")}
          </p>
        </div>
      </section>

      <section className="home__help">
        <h2 className="home__help--header">{t("home_help_header")}</h2>

        <div className="home__help--pic">
        <img src={picHelp1} alt={t("home_help_header")} className="home__help--pic1" />
        <img src={picHelp2} alt={t("home_help_header")} className="home__help--pic2" />
        <img src={picHelp3} alt={t("home_help_header")} className="home__help--pic3" />
        </div>

        <div className="home__help--container">
          <div className="home__help--container--block help_block1">
            <h4 className="home__help--container--block--header">
              {t("home_help_block1_header")}
            </h4>

            <p className="home__help--container--block--text">
              {t("home_help_block1_text")}
            </p>

            <Link to="/trips" className="home__help--container--block--button">
              {t("home_help_block_try")}
            </Link>
          </div>

          <div className="home__help--container--block help_block2">
            <h4 className="home__help--container--block--header">
              {t("home_help_block2_header")}
            </h4>

            <p className="home__help--container--block--text">
              {t("home_help_block2_text")}
            </p>

            <Link to="/trips" className="home__help--container--block--button">
              {t("home_help_block_try")}
            </Link>
          </div>

          <div className="home__help--container--block help_block3">
            <h4 className="home__help--container--block--header">
              {t("home_help_block3_header")}
            </h4>

            <p className="home__help--container--block--text">
              {t("home_help_block3_text")}
            </p>

            <Link to="/trips" className="home__help--container--block--button">
              {t("home_help_block_try")}
            </Link>
          </div>
        </div>
      </section>

      <section className="home__mission">
        <h2 className="home__mission--header">{t("home_mission_header")}</h2>

        <p className="home__mission--text">{t("home_mission_text1")}</p>

        <p className="home__mission--text">{t("home_mission_text2")}</p>

        <Link to="/trips" className="home__help--container--block--button try-button">
          {t("home_help_block_try")}
        </Link>

        <img src={picHome} alt="" className="home__mission--pic" />
      </section>
    </main>
  );
};
