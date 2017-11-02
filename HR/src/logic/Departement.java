package logic;



public class Departement {
		
		protected int dept_id;
		protected String deptnaam;
		protected Personeel depthoofd;
		protected Adres adres;
		
		Departement(int dept_id, String deptnaam, Personeel depthoofd, Adres adres)
		{
			this.dept_id = dept_id;
			this.deptnaam = deptnaam;
			this.depthoofd = depthoofd;
			this.adres = adres;
		}

		public int getDept_id() {
			return dept_id;
		}

		public void setDept_id(int dept_id) {
			this.dept_id = dept_id;
		}

		public String getDeptnaam() {
			return deptnaam;
		}

		public void setDeptnaam(String deptnaam) {
			this.deptnaam = deptnaam;
		}

		public Personeel getDepthoofd() {
			return depthoofd;
		}

		public void setDepthoofd(Personeel depthoofd) {
			this.depthoofd = depthoofd;
		}

		public Adres getAdres() {
			return adres;
		}

		public void setAdres(Adres adres) {
			this.adres = adres;
		}
		
		
		
		public String toString()
		{
			String gegevens = "Hieronder kan u de informatie over dit departement terugvinden:" + "\n" + "-Departementsid: " + this.dept_id + "\n" + "Departementsnaam: " + this.deptnaam + "\n" + "Departementshoofd: " + this.depthoofd.toString() + "\n" + "Adres: " + "\n" + this.adres.toString();
		}

	}
