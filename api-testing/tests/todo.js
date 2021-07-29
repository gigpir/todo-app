const chai = require('chai');
const chaiHttp = require('chai-http');

const should = chai.should();

const api_path = require('../urls').api_path;

chai.use(chaiHttp);

console.log(api_path.toString());

const api_server = chai.request(api_path.toString());

// Our parent block
describe('Todos', () => {
  /**
   * Test the /GET route
   **/
  describe('/GET', () => {
    it('It should get a 401', (done) => {
      api_server.get('/').end((err, res) => {
        res.should.have.status(401);
        // res.body.should.be.a('array');
        // res.body.length.should.be.eql(0);
        done();
      });
    });
  });
});
