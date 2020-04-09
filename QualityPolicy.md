### Quality Policy
> Describe your Quality Policy in detail for this Sprint (remember what I ask you to do when I talk about the "In your Project" part in the lectures and what is mentioned after each assignment (in due course you will need to fill out all of them, check which ones are needed for each Deliverable). You should keep adding things to this file and adjusting your policy as you go.

**GitHub Workflow** (due Feb 2nd)
  > Each sprint will have a dedicated branch, pulled from the master branch.  It will be named 'sprintX' where X is the current sprint iteration number.
  > Each user story will have an independent branch pulled from 'sprintX' and be named 'US_X' where X is the user story number generated from Taiga
  > If multiple developers are working on the same user story but different tasks, separate branches shall be generated (1 for each developer) and named 'TASK_X' where TaskName aligns with the task being worked
  > Upon the completion of a user story, the sprintX branch shall be merged into the usX branch and any conflicts shall be resolved.  Upon successful testing, a pull request will be generated to have the changes merged into sprintX branch.
  > At the completion of the sprint, the sprintX branch will be thoroughly tested.  Upon completion of testing, the branch will be merged into master by the appointed Git Master

  > Commit Comments: All commit comments shall start with TASK# and action description of changes. Example: "45 - commenting out bad button"

  > Conflicts: All conflicts are to be handled by the developer responsible for generating the pull request.

  > Code Removal: Before removing code, comment out the lines of code no longer being used, and start with a //TODO comment header, Developer Name, and reason for removal for cleanup in last sprint

  > New classes do need to include @author in Javadoc comments
  > Submit pull request when moving a ticket over to Ready For Test, Tag @everyone in Git channel. Once tested and reviewed, Reviewer will add a comment in PR with feedback or approval.
  > After pull request is approved by the Tester/Reviewer, Approver merges the ticket in, closes the ticket in Taiga


**Unit Tests Blackbox** (due start Sprint 2)
  > Whoever writes the Blackbox tests for the class is NOT the developer that created the class
  > JavaDoc comments need to be extremely clear on parameters passed in, and general functionality to create these tests

 **Unit Tests Whitebox** (due Feb 25th)
  > Your Whitebox testing policy 

**Code Review** (due Feb 25th)
  > Your Code Review policy   

  > Include a checklist/questions list which every developer will need to fill out/answe when creating a Pull Request to the Dev branch. 

  > Include a checklist/question list which every reviewer will need to fill out/anser when conducting a review, this checklist (and the answers of course) need to be put into the Pull Request review.

**Static Analysis**  (due start Sprint 3)
  > Your Static Analysis policy   

**Continuous Integration**  (due start Sprint 3)
  > Your Continuous Integration policy
