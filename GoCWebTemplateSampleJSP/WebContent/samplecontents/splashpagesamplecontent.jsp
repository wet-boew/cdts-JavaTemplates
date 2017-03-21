<script type="text/javascript">
    var contentSplash = document.getElementById("splashContent");
    contentSplash.innerHTML = wet.builder.splash({
        cdnEnv: "prod",
        indexEng: "index.action",
        indexFra: "index.action",
        termsEng: "http://www.canada.ca/en/transparency/terms.html",
        termsFra: "http://www.canada.ca/fr/transparence/avis.html",
        nameEng: "[My web asset]",
        nameFra: "[Mon actif web]"
    });
</script>